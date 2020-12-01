package com.fss.openbanking.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.openbanking.adapter.RestAdapter;
import com.fss.openbanking.bean.AccountAdapter;
import com.fss.openbanking.bean.AccountData;
import com.fss.openbanking.bean.AccountIdentifications;
import com.fss.openbanking.bean.AccountsResponse;
import com.fss.openbanking.bean.AdapterResponse;
import com.fss.openbanking.bean.AuthenticationBean;
import com.fss.openbanking.bean.ConsentResponse;
import com.fss.openbanking.bean.InstitutionDetails;
import com.fss.openbanking.bean.TppPaymentsResponse;
import com.fss.openbanking.bean.UserDetails;
import com.fss.openbanking.constants.TppConstants;
import com.fss.openbanking.dao.DaoRepository;
import com.fss.openbanking.responseformatter.AccountsResponseFormatter;
import com.fss.openbanking.utils.RRN;

@Service("paymentsService")
public class PaymentsServiceImpl implements PaymentsService {

	public static final Logger LOGGER = LoggerFactory.getLogger(PaymentsServiceImpl.class);
	
	@Autowired
	private DaoRepository daoRepository;
	
	@Autowired
	private RestAdapter restAdapter;
	
	@Autowired
	private AccountsResponseFormatter accountsResponseFormatter;
	
	@Override
	public TppPaymentsResponse fetchPaymentAccounts(UserDetails userDetails) {
		TppPaymentsResponse tppPaymentsResponse = new TppPaymentsResponse();
		try
		{
			List<InstitutionDetails> instDetailsfromDb = daoRepository.fetchTppInstDetails();
			
			if(instDetailsfromDb.isEmpty()) {
				tppPaymentsResponse.setResponseFlag("2");
				tppPaymentsResponse.setResponseMessage("You are not linked your Bank Accounts Detail with us.");
				throw new Exception("Institution details not found");
			} 
				
			tppPaymentsResponse.setResponseFlag("1");
			List<AccountsResponse> accountsList = new ArrayList<>();
			accountsList = new ArrayList<>();
			for(InstitutionDetails institutionDetails : instDetailsfromDb)
			{
				AccountsResponse accountsResponse = null;
	
				List<AccountData> accountDetails = daoRepository.fetchAccountDetailsByBank(institutionDetails.getBankId(),userDetails.getMobileNumber());
				
				for(AccountData accountDetail : accountDetails)
				{
					
					accountsResponse = new AccountsResponse();
					accountsResponse.setBankName(institutionDetails.getBankName());
					accountsResponse.setMobileNumber(accountDetail.getMobileNumber());
					accountsResponse.setBankId(accountDetail.getBankId());
					accountsResponse.setAccountId(accountDetail.getId());
					accountsResponse.setLogoUrl(institutionDetails.getBankLogo());
					accountsResponse.setIconUrl(institutionDetails.getBankIcon());
					accountsList.add(accountsResponse);
				}
			}	
			tppPaymentsResponse.setAccountsResponseList(accountsList);
		}
		catch(Exception e)
		{
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppPaymentsResponse;
	}

	@Override
	public TppPaymentsResponse createPaymentAuthorization(AuthenticationBean authenticationBean, UserDetails userDetails) {
		TppPaymentsResponse tppPaymentsResponse = new TppPaymentsResponse();
		try
		{
			AdapterResponse adapterResponse = null;
			AccountData accountDetail = new AccountData();
			accountDetail.setBankId(authenticationBean.getBankId());
			accountDetail.setMobileNumber(userDetails.getMobileNumber());
			accountDetail.setId(authenticationBean.getAccountNumber());
			
			List<AccountIdentifications> accountIdentifications = daoRepository.fetchAccountIdentificationsDetails(accountDetail);
			
			if(accountIdentifications.isEmpty())
			{
				tppPaymentsResponse.setResponseFlag("0");
				throw new Exception("Account Identication not found");
			}
			
			JSONObject payAuthReq = generatePaymentAuthRequest(userDetails,authenticationBean,accountIdentifications);
			
			if(payAuthReq == null)
			{
				tppPaymentsResponse.setResponseFlag("0");
				throw new Exception("Payment Authorization request body build failed");
			}
			
			AccountAdapter accountAdapter = new AccountAdapter();
			
			accountAdapter.setUrl( TppConstants.yapilyUrl + "/" + TppConstants.payAuthUrl);
			
			adapterResponse = restAdapter.callPayAuthorization(accountAdapter,payAuthReq);
			
			if(!"201".equalsIgnoreCase(adapterResponse.getStatusCode())) 
			{
				tppPaymentsResponse.setResponseFlag("0");
				throw new Exception("Cannot Initiate Payment Authorization");
			}
			
			ConsentResponse consentResponse = accountsResponseFormatter.formatConsentInitiationResponse(adapterResponse.getData());
			tppPaymentsResponse.setResponseFlag("1");
			JSONObject rechargeDetails = new JSONObject();
			rechargeDetails.put("accountNumber", authenticationBean.getAccountNumber());
			rechargeDetails.put("bankName", authenticationBean.getBankName());
			rechargeDetails.put("provider", authenticationBean.getProvider());
			rechargeDetails.put("mobileNumber", authenticationBean.getMobileNumber());
			rechargeDetails.put("amount", authenticationBean.getAmount());
			tppPaymentsResponse.setPaymentData(payAuthReq.get("paymentRequest") + "=" + rechargeDetails.toString());
			tppPaymentsResponse.setAuthorizationUrl(consentResponse.getAuthorisationUrl());
		}
		catch(Exception e)
		{
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
	return tppPaymentsResponse;
	}
	
	public JSONObject generatePaymentAuthRequest(UserDetails userDetails, AuthenticationBean authenticationBean, List<AccountIdentifications> accountIdentifications) {
		JSONObject jsonRequest = null;
		try
		{
			String uuId = daoRepository.getUuIdusingMobileNumber(userDetails.getMobileNumber());
			
			if(uuId == null)
			{
				throw new Exception("UUID not found - Mobile Number : "+userDetails.getMobileNumber());
			}
			jsonRequest = new JSONObject();
			
			jsonRequest.put("userUuid", uuId);
			jsonRequest.put("institutionId", authenticationBean.getBankId());
			jsonRequest.put("callback", TppConstants.callBackUrl);
			jsonRequest.put("oneTimeToken", "false");
			
			JSONObject paymentRequest = new JSONObject();
			
			paymentRequest.put("paymentIdempotencyId", RRN.randomAlphaNumeric(32));
			paymentRequest.put("reference", "reference");
			paymentRequest.put("type", TppConstants.paymentType);
			
			//Amount details
			paymentRequest.put("amount", new JSONObject().put("amount", authenticationBean.getAmount()).put("currency", "GBP"));

			// Account Identification details
			JSONArray accountIdentifier = new JSONArray();
			for(AccountIdentifications acIdentify: accountIdentifications)
			{
				accountIdentifier.put(new JSONObject().put("type", acIdentify.getType()).put("identification", acIdentify.getIdentification()));
			}
			
			// Payer details
			JSONObject payer = new JSONObject();
			payer.put("name", userDetails.getUserName());
			payer.put("accountIdentifications", accountIdentifier);
			paymentRequest.put("payer", payer);
			
			// Payee details
			JSONObject payee = new JSONObject();
				payee.put("name", "vijay");
				
				JSONObject address = new JSONObject();
				address.put("country", "GB");
				payee.put("address", address);
				
				payee.put("accountIdentifications", accountIdentifier);
				
			paymentRequest.put("payee", payee);
			
			jsonRequest.put("paymentRequest", paymentRequest);
	
			LOGGER.info("Create PayAuth Request ::::" + jsonRequest.toString());
		}
		catch(Exception e)
		{
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}

		return jsonRequest;
	}

}
