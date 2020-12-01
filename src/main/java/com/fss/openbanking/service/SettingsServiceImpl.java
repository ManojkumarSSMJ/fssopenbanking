/**
 * 
 */
package com.fss.openbanking.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.openbanking.adapter.RestAdapter;
import com.fss.openbanking.bean.AccountAdapter;
import com.fss.openbanking.bean.AccountData;
import com.fss.openbanking.bean.AccountRequest;
import com.fss.openbanking.bean.AccountsResponse;
import com.fss.openbanking.bean.AdapterResponse;
import com.fss.openbanking.bean.ConsentDetails;
import com.fss.openbanking.bean.ConsentResponse;
import com.fss.openbanking.bean.ConsentTokenDetails;
import com.fss.openbanking.bean.InstitutionDetails;
import com.fss.openbanking.bean.TppConsentForm;
import com.fss.openbanking.bean.TppConsentResponse;
import com.fss.openbanking.bean.UpdateDTDetails;
import com.fss.openbanking.bean.UserDetails;
import com.fss.openbanking.constants.TppConstants;
import com.fss.openbanking.dao.DaoRepository;
import com.fss.openbanking.responseformatter.AccountsResponseFormatter;
import com.fss.openbanking.utils.OpenBankingUtility;
import com.fss.openbanking.utils.RRN;

/**
 * @author selvakumara
 *
 */
@Service("SettingsService")
public class SettingsServiceImpl implements SettingsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SettingsService.class);

	@Autowired
	private DaoRepository daoRepository;
	
	@Autowired
	private RestAdapter restAdapter;
	
	@Autowired
	private AccountsResponseFormatter accountsResponseFormatter; 
	
	@Override
	public List<InstitutionDetails> fetchInstDetails(String userId) {
		List<InstitutionDetails> instDetails = new ArrayList<InstitutionDetails>();
		try {
			List<InstitutionDetails> instDetailsfromDb = daoRepository.fetchTppInstDetails();
			if(instDetailsfromDb != null && !instDetailsfromDb.isEmpty()) {
				for(InstitutionDetails instDetail: instDetailsfromDb) {
					instDetail.setConsentDetails(fetchInstFeatureDetailsByBankId(instDetail.getBankId()));
					instDetail.setNonce(RRN.genRRN()+"");
					instDetail.setState(RRN.randomAlphaNumeric(11));
					instDetails.add(instDetail);
				}
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return instDetails;
	}

	private List<ConsentDetails> fetchInstFeatureDetailsByBankId(String bankId) {
		List<ConsentDetails> consentDetails = new ArrayList<ConsentDetails>();
		try {
			consentDetails = daoRepository.fetchInstFeatureDetailsByBankId(bankId);
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return consentDetails;
	}

	@Override
	public TppConsentResponse processTppConsentResponse(UserDetails userDetails, TppConsentForm tppConsentForm) {
		TppConsentResponse tppConsentResponse = new TppConsentResponse();
		tppConsentResponse.setResponseFlag("0");
		try {
			String expiryISODate = "";
			String startISODate = "";
			String endISODate = "";
			String uuid = null;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date myDate = new Date(System.currentTimeMillis());
			if(!"".equals(tppConsentForm.getExpiryDate()) && !"".equals(tppConsentForm.getTransactionStartDate()) && !"".equals(tppConsentForm.getTransactionEndDate())) {
				
				expiryISODate = OpenBankingUtility.convertDateToDDMMYYYYToYYYYMMDD(tppConsentForm.getExpiryDate());
				expiryISODate = OpenBankingUtility.convertDateAsISO(expiryISODate);
				
				startISODate = OpenBankingUtility.convertDateToDDMMYYYYToYYYYMMDD(tppConsentForm.getTransactionStartDate());
				startISODate = OpenBankingUtility.convertDateAsISO(startISODate);
				

				endISODate = OpenBankingUtility.convertDateToDDMMYYYYToYYYYMMDD(tppConsentForm.getTransactionEndDate());
				endISODate = OpenBankingUtility.convertDateAsISO(endISODate);

			}
			else
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(myDate);
				cal.add(Calendar.MONTH , -6);
				expiryISODate = OpenBankingUtility.convertDateToDDMMYYYYToYYYYMMDD(dateFormat.format(cal.getTime()));
				expiryISODate = OpenBankingUtility.convertDateAsISO(expiryISODate);
				
				cal = Calendar.getInstance();
				cal.setTime(myDate);
				expiryISODate = OpenBankingUtility.convertDateToDDMMYYYYToYYYYMMDD(dateFormat.format(cal.getTime()));
				expiryISODate = OpenBankingUtility.convertDateAsISO(expiryISODate);
				
			    cal = Calendar.getInstance();
				cal.setTime(myDate);
				cal.add(Calendar.MONTH, 6);
				expiryISODate = OpenBankingUtility.convertDateToDDMMYYYYToYYYYMMDD(dateFormat.format(cal.getTime()));
				expiryISODate = OpenBankingUtility.convertDateAsISO(expiryISODate);
			}
			
			uuid = daoRepository.getUuIdusingMobileNumber(userDetails.getMobileNumber());
			
			if(uuid == null)
			{
				tppConsentResponse.setResponseFlag("0");
				tppConsentResponse.setResponseMessage("Sorry Consent Updation failed");
				throw new Exception("Sorry Consent Updation failed");
			}
			
			AccountAdapter accountAdapter = new AccountAdapter();
			accountAdapter.setUrl(TppConstants.yapilyUrl + "/" + TppConstants.accAuthUrl);
			accountAdapter.setUserUuid(uuid);
			accountAdapter.setInstitutionId(tppConsentForm.getBankId());
			accountAdapter.setCallback(TppConstants.callBackUrl);
			
			AccountRequest accountRequest = new AccountRequest();
			accountRequest.setExpiresAt(expiryISODate);
			accountRequest.setTransactionFrom(startISODate);
			accountRequest.setTransactionTo(endISODate);
			accountRequest.setFeatureScope(tppConsentForm.getSelectedConsents().split("\\|"));
			
			accountAdapter.setAccountRequest(accountRequest);
			
			AdapterResponse adapterResponse = restAdapter.callConsentInitiation(accountAdapter);
			
			if(!"201".equalsIgnoreCase(adapterResponse.getStatusCode())) {
				tppConsentResponse.setResponseFlag("0");
				tppConsentResponse.setResponseMessage("Sorry Consent Updation failed");
				throw new Exception("Sorry Consent Updation failed");
			}
			
			ConsentResponse consentResponse = accountsResponseFormatter.formatConsentInitiationResponse(adapterResponse.getData());
			boolean saveTempConsent = daoRepository.saveConsentTemp(consentResponse);
			if(!saveTempConsent) {
				tppConsentResponse.setResponseFlag("0");
				tppConsentResponse.setResponseMessage("Sorry Consent Updation failed");
				throw new Exception("Sorry Consent Updation failed");
			}
			tppConsentResponse.setAccountData(adapterResponse.getData());
			tppConsentResponse.setResponseFlag("1");
			tppConsentResponse.setResponseMessage("Consent Updated successfully");
			tppConsentResponse.setAuthenticateUrl(consentResponse.getAuthorisationUrl());
			
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppConsentResponse;
	}

	@Override
	public List<ConsentTokenDetails> fetchExistingConsentDetails(UserDetails userDetails) {
		List<ConsentTokenDetails> consentTknDetails = new ArrayList<ConsentTokenDetails>();
		try {
			List<AccountData> accountDatas = null;
			List<AccountsResponse> accountsResponses = new ArrayList<>();
			AccountsResponse accResp = null;
			List<ConsentTokenDetails> consentDetailsfromDb = daoRepository.fetchConsentDetailsByUser(userDetails.getMobileNumber());
			if(!consentDetailsfromDb.isEmpty()) {
				List<ConsentDetails> consentDetails = null;
				for(ConsentTokenDetails consentDetail: consentDetailsfromDb) {
					accountsResponses = new ArrayList<>();
					consentDetails = daoRepository.fetchConsentDetailsByBank(consentDetail);
					consentDetail.setConsentDetails(consentDetails);
					consentDetail.setNonce(RRN.genRRN()+"");
					consentDetail.setState(RRN.randomAlphaNumeric(11));
					consentDetail.setSelectAll(iterateConsentDetails(consentDetails));
					accountDatas = daoRepository.fetchAccountDetailsByBank(consentDetail.getBankId(),consentDetail.getMobileNumber());
					
					for(AccountData accountData : accountDatas)
					{
						accResp = new AccountsResponse();
						accResp.setAccountSubType(accountData.getAccountType());
						accResp.setMaskAccountId(OpenBankingUtility.mask(accountData.getId()));
						
						accountsResponses.add(accResp);
					}
					if(!accountsResponses.isEmpty())
						consentDetail.setAccountDetails(accountsResponses);
					consentTknDetails.add(consentDetail);
				}
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return consentTknDetails;
	}
	
	String iterateConsentDetails(List<ConsentDetails> consentDetails) {
		String selectAll="N";
		try {
			int size = consentDetails.size();
			int sucCount = 0;
			for(ConsentDetails consentDetail: consentDetails) {
				if("Y".equals(consentDetail.getConsentStatus())) {
					sucCount++;
				}
			}
			if(sucCount == size) {
				selectAll = "Y";
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return selectAll;
	}

	@Override
	public TppConsentResponse revokeConsent(String userId, TppConsentForm tppConsentForm) {
		TppConsentResponse tppConsentResponse = new TppConsentResponse();
		try {
			InstitutionDetails instDetails = new InstitutionDetails();
			ConsentTokenDetails existingConsentDetails = daoRepository.fetchConsentBankId(tppConsentForm.getBankId(),tppConsentForm.getMobileNumber());
			AccountAdapter accountAdapter = new AccountAdapter();
			accountAdapter.setUrl(TppConstants.yapilyUrl+"/consents/"+existingConsentDetails.getConsentId()+"?forceDelete=false");
			AdapterResponse adapterResponse = restAdapter.callDeleteConsent(accountAdapter);
			
			if(!"200".equalsIgnoreCase(adapterResponse.getStatusCode())) {
				tppConsentResponse.setResponseFlag("0");
				tppConsentResponse.setResponseMessage("Sorry " + instDetails.getBankName() + " consent revoking failed");
				throw new Exception("Sorry " + instDetails.getBankName() + " consent revoking failed");
			}
				
		    instDetails = daoRepository.fetchInstDetailsByBankId(tppConsentForm.getBankId());
			
		    daoRepository.deleteConsentTemp(tppConsentForm);
			
			tppConsentResponse.setResponseFlag("1");
			tppConsentResponse.setResponseMessage(instDetails.getBankName()+" consent revoked successfully");
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppConsentResponse;
	}

	@Override
	public TppConsentResponse processAccountTokenConsent(UserDetails userDetails, TppConsentForm tppConsentForm, String token) {
		TppConsentResponse tppConsentResponse = new TppConsentResponse();
		try {
			
			ConsentResponse consentResponse = accountsResponseFormatter.formatConsentInitiationResponse(userDetails.getAccountData());
			boolean tokenInsert = daoRepository.saveTokenDetails(consentResponse,token);
			if(!tokenInsert)
			{
				tppConsentResponse.setResponseFlag("0");
				tppConsentResponse.setResponseMessage("Sorry " + tppConsentForm.getBankName() +" is not provide the consent id");
				throw new Exception("Sorry " + tppConsentForm.getBankName() +" is not provide the consent id");
			}
			
			tppConsentResponse.setResponseFlag("1");
			tppConsentResponse.setResponseMessage("Consent Updated successfully");
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppConsentResponse;
	}

	@Override
	public TppConsentResponse processPaymentTokenConsent(UserDetails userDetails, TppConsentForm tppConsentForm, String token) {
		TppConsentResponse tppConsentResponse = new TppConsentResponse();
		try {
			AdapterResponse adapterResponse = null;
			
			AccountAdapter accountAdapter = new AccountAdapter();
			
			accountAdapter.setUrl( TppConstants.yapilyUrl + "/" + TppConstants.paymentUrl);
			accountAdapter.setToken(token);
			accountAdapter.setPaymentData(userDetails.getPaymentData().split("=")[0]);
			adapterResponse = restAdapter.callPayment(accountAdapter);
			
			if(!"201".equalsIgnoreCase(adapterResponse.getStatusCode())) 
			{
				tppConsentResponse.setResponseFlag("0");
				throw new Exception("Error from payment request");
			}
			JSONObject responseJSONObject = new JSONObject(adapterResponse.getData().toString());
			if(!responseJSONObject.has("data")) 
			{
				tppConsentResponse.setResponseFlag("0");
				throw new Exception("Payment request data not found");
			}
			tppConsentResponse.setTransactionId(new JSONObject(responseJSONObject.get("data").toString()).getString("id"));
			tppConsentResponse.setResponseFlag("1");
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppConsentResponse;
	}

	@Override
	public TppConsentResponse fetchfetchActivitiesDetails(UserDetails userDetails) {
		TppConsentResponse tppConsentResponse = new TppConsentResponse();
		try {
			List<InstitutionDetails> instDetailsfromDb = daoRepository.fetchTppInstDetails();
			
		    List<UpdateDTDetails> updateDTDetailsList = daoRepository.fetchUpdatedDetails(userDetails);
		    
		    tppConsentResponse.setUpdateDTDetailsList(updateDTDetailsList);
		    tppConsentResponse.setInstitutionDetailsList(instDetailsfromDb);
		    
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return tppConsentResponse;
	}

}


