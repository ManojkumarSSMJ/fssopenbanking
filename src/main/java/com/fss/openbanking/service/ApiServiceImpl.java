/**
 * 
 */
package com.fss.openbanking.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fss.openbanking.adapter.RestAdapter;
import com.fss.openbanking.bean.AccountAdapter;
import com.fss.openbanking.bean.AccountBalances;
import com.fss.openbanking.bean.AccountData;
import com.fss.openbanking.bean.AdapterResponse;
import com.fss.openbanking.bean.ConsentTokenDetails;
import com.fss.openbanking.bean.DirectDebitData;
import com.fss.openbanking.bean.InstitutionData;
import com.fss.openbanking.bean.InstitutionDetails;
import com.fss.openbanking.bean.LoggingDetails;
import com.fss.openbanking.bean.StandingOrderData;
import com.fss.openbanking.bean.TppAccountsRequest;
import com.fss.openbanking.bean.TppAccountsResponse;
import com.fss.openbanking.bean.TransactionData;
import com.fss.openbanking.bean.UserDetails;
import com.fss.openbanking.bean.UserResponse;
import com.fss.openbanking.constants.TppConstants;
import com.fss.openbanking.dao.DaoApiRepository;
import com.fss.openbanking.dao.DaoRepository;
import com.fss.openbanking.exception.TechnicalDeclineException;
import com.fss.openbanking.responseformatter.AccountsResponseFormatter;
import com.fss.openbanking.runner.FSSOpenBankingRunner;
import com.fss.openbanking.utils.RRN;

/**
 * @author selvakumara
 *
 */
@Service("ApiService")
public class ApiServiceImpl implements ApiService {

	public static final Logger LOGGER = LoggerFactory.getLogger(ApiServiceImpl.class);

	@Autowired
	Environment environment;

	@Autowired
	private DaoRepository daoRepository;
	
	@Autowired
	private DaoApiRepository daoApiRepository;
	
	@Autowired
	private RestAdapter restAdapter;
	
	@Autowired
	private AccountsResponseFormatter accountsResponseFormatter; 
	
	@Override
	public TppAccountsResponse updateBankDetailsByBank(UserDetails userDetails, TppAccountsRequest tppAccountsRequest) {
		TppAccountsResponse tppAccountsResponse = new TppAccountsResponse();
		tppAccountsResponse.setResponseFlag("0");
		try {
			 ConsentTokenDetails consentTokenDetails = daoRepository.fetchConsentBankId(tppAccountsRequest.getBankId(),userDetails.getMobileNumber());
			
			if(consentTokenDetails == null) {
				tppAccountsResponse.setResponseFlag("2");
				throw new Exception("Consent Details not found");
			} 
				
			if(consentTokenDetails.getConsentToken() == null)
			{
				tppAccountsResponse.setResponseFlag("2");
				throw new Exception("Consent Token not found");
			}
			
		   consentTokenDetails.setBankName(tppAccountsRequest.getBankName());
			
		   if(!"-".equals(tppAccountsRequest.getLastUpdateDT()))
		   {
			   String instUpdateHrs = daoRepository.fetchUpdateHoursfromInstitutions(consentTokenDetails);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(tppAccountsRequest.getLastUpdateDT());
				Calendar c1 = Calendar.getInstance();
				c1.setTime(date);
				c1.add(Calendar.HOUR, Integer.parseInt(instUpdateHrs));
				
				Calendar c2 = Calendar.getInstance();
				
				int compare = c1.compareTo(c2);
				
				if(compare > 0)
				{
					tppAccountsResponse.setResponseFlag("1");
					throw new Exception("Upto date");
				}
		   }
		   
		   processAccountDeatils(tppAccountsResponse,consentTokenDetails);
			
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppAccountsResponse;
	}
	
	@Override
	public TppAccountsResponse tppUpdateBankDetails(UserDetails userDetails) {
		TppAccountsResponse tppAccountsResponse = new TppAccountsResponse();
		try {
			List<InstitutionDetails> institutionsList = daoRepository.fetchTppInstDetails();
			
			if(institutionsList.isEmpty()) {
				tppAccountsResponse.setResponseFlag("0");
				throw new Exception("Institution Deatils not found");
			} 
			
			ConsentTokenDetails consentTokenDetails = null;
			
			for(InstitutionDetails institutionDetails : institutionsList) {
				
				consentTokenDetails = daoRepository.fetchConsentBankId(institutionDetails.getBankId(),userDetails.getMobileNumber());
				
				if(consentTokenDetails == null)
					continue;
				
				if(consentTokenDetails.getConsentToken() == null)
					continue;
				tppAccountsResponse.setResponseFlag("1");
				processAccountDeatils(tppAccountsResponse,consentTokenDetails);
					
			}
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return tppAccountsResponse;
	}

	
	private boolean processAccountDeatils(TppAccountsResponse tppAccountsResponse, ConsentTokenDetails consentTokenDetails) {
		boolean result = false;
		try {
			LoggingDetails loggingDetails = new LoggingDetails();
			AccountAdapter accountAdapter = null;
			AdapterResponse adapterResponse = null;
			
			loggingDetails.setBankId(consentTokenDetails.getBankId());
			loggingDetails.setTransactionDesc("Fetch Accounts"); // set txn desc
			loggingDetails.setReqInitiatedDt(new Timestamp(new Date().getTime())); // set Request Initiated time
			
			accountAdapter = new AccountAdapter();
			accountAdapter.setUrl( TppConstants.yapilyUrl + "/accounts" );
			accountAdapter.setToken(consentTokenDetails.getConsentToken());
			adapterResponse = restAdapter.callAccountsDetailsApi(accountAdapter);
			
			loggingDetails.setRespArrivalDt(new Timestamp(new Date().getTime())); // set Response Arrival time
			
			if(!"200".equalsIgnoreCase(adapterResponse.getStatusCode())) {
				loggingDetails.setReqProcessingDt(new Timestamp(new Date().getTime()).getTime() - loggingDetails.getReqInitiatedDt().getTime() + ""); // set Request Processing time in ML
				loggingFetchingDetails(adapterResponse.getData(),loggingDetails,adapterResponse.getStatusCode(),adapterResponse.getStatusMsg(),adapterResponse.getStatusFlag());
				loggingDetails.setTransactionDesc("Fetch Balance"); // set balance desc
				loggingFetchingDetails(adapterResponse.getData(),loggingDetails,adapterResponse.getStatusCode(),adapterResponse.getStatusMsg(),adapterResponse.getStatusFlag());
				loggingDetails.setTransactionDesc("Fetch Transactions"); // set txn desc
				loggingFetchingDetails(adapterResponse.getData(),loggingDetails,adapterResponse.getStatusCode(),adapterResponse.getStatusMsg(),adapterResponse.getStatusFlag());
				loggingDetails.setTransactionDesc("Fetch DirectDebits"); // set txn desc
				loggingFetchingDetails(adapterResponse.getData(),loggingDetails,adapterResponse.getStatusCode(),adapterResponse.getStatusMsg(),adapterResponse.getStatusFlag());
				loggingDetails.setTransactionDesc("Fetch StandingOrders"); // set txn desc
				loggingFetchingDetails(adapterResponse.getData(),loggingDetails,adapterResponse.getStatusCode(),adapterResponse.getStatusMsg(),adapterResponse.getStatusFlag());
				throw new Exception(adapterResponse.getStatusMsg());
			}
			
			List<AccountData> accountsDataList = accountsResponseFormatter.formatAccountsResponse(adapterResponse.getData());
			
			for(AccountData accountData : accountsDataList)
			{
				if(daoApiRepository.saveAccountDetails(consentTokenDetails,accountData))
					if(daoApiRepository.saveAccountNameDetails(consentTokenDetails,accountData))
						if(daoApiRepository.saveAccountIdentificationsDetails(consentTokenDetails,accountData))
							if(daoApiRepository.saveAccountBalanceDetails(consentTokenDetails,accountData))
								result = true;
			}
			
			loggingDetails.setReqProcessingDt(new Timestamp(new Date().getTime()).getTime() - loggingDetails.getReqInitiatedDt().getTime() + ""); // set Request Processing time in ML
			
			if(!result)
			{
			    loggingFetchingDetails(adapterResponse.getData(),loggingDetails,TppConstants.failureCode,"Accounts Details Fetching Failed",TppConstants.failure);
			    throw new Exception("Accounts Details Fetching Failed");
			}
			loggingFetchingDetails(adapterResponse.getData(),loggingDetails,TppConstants.successCode,"Accounts Details Fetched Successfully",TppConstants.success);
			
			// Update balance Details
			tppUpdatebalanceDetails(accountsDataList, consentTokenDetails);
			// Update Txn Details
			tppUpdateTransactionDetails(accountsDataList, consentTokenDetails);
			// Update DirectDebits Details
			tppUpdateDirectDebitsDetails(accountsDataList, consentTokenDetails);
			// Update Standing orders Details
			tppUpdateStandingOrdersDetails(accountsDataList, consentTokenDetails);
			
		    daoApiRepository.saveLastUpdatedDateTime(consentTokenDetails);
		}catch(Exception e) {
				LOGGER.error("catch block");
				LOGGER.error("Failed!", e.getMessage());
				tppAccountsResponse.setResponseFlag("0");
			}
		
		return result;
	}

	private boolean tppUpdatebalanceDetails(List<AccountData> accountsDataList, ConsentTokenDetails consentTokenDetails) {
		
		boolean balanceDetails = false;
		try {
			LoggingDetails loggingDetails = new LoggingDetails();
			loggingDetails.setTransactionDesc("Fetch Balance"); // set balance desc

			
			for(AccountData accountData : accountsDataList)
			{
				loggingDetails.setBankId(consentTokenDetails.getBankId());
				loggingDetails.setReqInitiatedDt(new Timestamp(new Date().getTime())); // set Request Initiated time
				
				AccountAdapter accountAdapter = new AccountAdapter();
				accountAdapter.setUrl( TppConstants.yapilyUrl + "/" + TppConstants.accountsUrl + "/" + accountData.getId() + "/" + TppConstants.balancesUrl);
				accountAdapter.setToken(consentTokenDetails.getConsentToken());
				AdapterResponse adapterResponse = restAdapter.callAccountsDetailsApi(accountAdapter);
				
				loggingDetails.setRespArrivalDt(new Timestamp(new Date().getTime())); // set Response Arrival time
				
				if(!"200".equalsIgnoreCase(adapterResponse.getStatusCode())) {
					loggingDetails.setReqProcessingDt(new Timestamp(new Date().getTime()).getTime() - loggingDetails.getReqInitiatedDt().getTime() + ""); // set Request Processing time in ML
					balanceDetails = loggingFetchingDetails(adapterResponse.getData(),loggingDetails,adapterResponse.getStatusCode(),adapterResponse.getStatusMsg(),adapterResponse.getStatusFlag());
					throw new Exception(adapterResponse.getStatusMsg());
				}
				
				List<AccountBalances> accountBalancesList= accountsResponseFormatter.formatBalanceDetails(adapterResponse.getData());
				
				accountData.setAccountBalances(accountBalancesList);
				balanceDetails = daoApiRepository.saveAccountBalanceDetails(consentTokenDetails,accountData);
				
				loggingDetails.setReqProcessingDt(new Timestamp(new Date().getTime()).getTime() - loggingDetails.getReqInitiatedDt().getTime() + ""); // set Request Processing time in ML
				
				if(!balanceDetails)
				{
					balanceDetails = loggingFetchingDetails(adapterResponse.getData(),loggingDetails,TppConstants.failureCode,"Balance Details Fetching Failed",TppConstants.failure);
					throw new Exception("Balance Details Fetching Failed");
				}
				balanceDetails = loggingFetchingDetails(adapterResponse.getData(),loggingDetails,TppConstants.successCode,"Balance Details Fetched Successfully",TppConstants.success);
			}
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return balanceDetails;
	}
	
	private boolean tppUpdateTransactionDetails(List<AccountData> accountsDataList, ConsentTokenDetails consentTokenDetails) {
		
		boolean transactionDetails = false;
		try {
			LoggingDetails loggingDetails = new LoggingDetails();
			loggingDetails.setTransactionDesc("Fetch Transactions"); // set txn desc

			
			for(AccountData accountData : accountsDataList)
			{
				loggingDetails.setBankId(consentTokenDetails.getBankId());
				loggingDetails.setReqInitiatedDt(new Timestamp(new Date().getTime())); // set Request Initiated time
				
				AccountAdapter accountAdapter = new AccountAdapter();
				accountAdapter.setUrl( TppConstants.yapilyUrl + "/" + TppConstants.accountsUrl + "/" + accountData.getId() + "/" + TppConstants.transactionsUrl);
				accountAdapter.setToken(consentTokenDetails.getConsentToken());
				AdapterResponse adapterResponse = restAdapter.callAccountsDetailsApi(accountAdapter);
				
				loggingDetails.setRespArrivalDt(new Timestamp(new Date().getTime())); // set Response Arrival time
				
				if(!"200".equalsIgnoreCase(adapterResponse.getStatusCode())) {
					loggingDetails.setReqProcessingDt(new Timestamp(new Date().getTime()).getTime() - loggingDetails.getReqInitiatedDt().getTime() + ""); // set Request Processing time in ML
					transactionDetails = loggingFetchingDetails(adapterResponse.getData(),loggingDetails,adapterResponse.getStatusCode(),adapterResponse.getStatusMsg(),adapterResponse.getStatusFlag());
					throw new Exception(adapterResponse.getStatusMsg());
				}
					
				List<TransactionData> TransactionDataList= accountsResponseFormatter.formatTransactionDetails(adapterResponse.getData());
				
				for(TransactionData transactionData : TransactionDataList)
					transactionDetails = daoApiRepository.saveTransactionDetails(transactionData,accountData,consentTokenDetails);

				loggingDetails.setReqProcessingDt(new Timestamp(new Date().getTime()).getTime() - loggingDetails.getReqInitiatedDt().getTime() + ""); // set Request Processing time in ML

				if(!transactionDetails)
				{
					transactionDetails = loggingFetchingDetails(adapterResponse.getData(),loggingDetails,TppConstants.failureCode,"Transactions Details Fetching Failed",TppConstants.failure);
					throw new Exception("Transactions Details Fetching Failed");
				}
			    transactionDetails = loggingFetchingDetails(adapterResponse.getData(),loggingDetails,TppConstants.successCode,"Transactions Details Fetched Successfully",TppConstants.success);
			}			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return transactionDetails;
	}
	
	private boolean tppUpdateDirectDebitsDetails(List<AccountData> accountsDataList,ConsentTokenDetails consentTokenDetails) {
		
		boolean directDebits = false;
		try {
			LoggingDetails loggingDetails = new LoggingDetails();
			loggingDetails.setTransactionDesc("Fetch DirectDebits"); // set txn desc

			for(AccountData accountData : accountsDataList)
			{
				loggingDetails.setBankId(consentTokenDetails.getBankId());
				loggingDetails.setReqInitiatedDt(new Timestamp(new Date().getTime())); // set Request Initiated time
				
				AccountAdapter accountAdapter = new AccountAdapter();
				accountAdapter.setUrl( TppConstants.yapilyUrl + "/" + TppConstants.accountsUrl + "/" + accountData.getId() + "/" + TppConstants.directDebitsUrl);
				accountAdapter.setToken(consentTokenDetails.getConsentToken());
				AdapterResponse adapterResponse = restAdapter.callAccountsDetailsApi(accountAdapter);
				
				loggingDetails.setRespArrivalDt(new Timestamp(new Date().getTime())); // set Response Arrival time
				
				if(!"200".equalsIgnoreCase(adapterResponse.getStatusCode())) {
					loggingDetails.setReqProcessingDt(new Timestamp(new Date().getTime()).getTime() - loggingDetails.getReqInitiatedDt().getTime() + ""); // set Request Processing time in ML
					directDebits = loggingFetchingDetails(adapterResponse.getData(),loggingDetails,adapterResponse.getStatusCode(),adapterResponse.getStatusMsg(),adapterResponse.getStatusFlag());
					throw new Exception(adapterResponse.getStatusMsg());
				}
					
				List<DirectDebitData> directDebitDataList = accountsResponseFormatter.formatDirectDebitDetails(adapterResponse.getData());
				
				for(DirectDebitData directDebitData : directDebitDataList)
					directDebits = daoApiRepository.saveDirectDebitDetails(directDebitData, accountData, consentTokenDetails);
	
				loggingDetails.setReqProcessingDt(new Timestamp(new Date().getTime()).getTime() - loggingDetails.getReqInitiatedDt().getTime() + ""); // set Request Processing time in ML
	
				if(!directDebits)
				{
					directDebits = loggingFetchingDetails(adapterResponse.getData(),loggingDetails,TppConstants.failureCode,"DirectDebits Details Fetching Failed",TppConstants.failure);
					throw new Exception("DirectDebits Details Fetching Failed");
				}
				directDebits = loggingFetchingDetails(adapterResponse.getData(),loggingDetails,TppConstants.successCode,"DirectDebits Details Fetched Successfully",TppConstants.success);
			}
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return directDebits;
	}
	
	private boolean tppUpdateStandingOrdersDetails(List<AccountData> accountsDataList, ConsentTokenDetails consentTokenDetails) {
		boolean standingOrders = false;
		try {
			LoggingDetails loggingDetails = new LoggingDetails();
			loggingDetails.setTransactionDesc("Fetch StandingOrders"); // set txn desc
			
			for(AccountData accountData : accountsDataList)
			{
				loggingDetails.setBankId(consentTokenDetails.getBankId());
				loggingDetails.setReqInitiatedDt(new Timestamp(new Date().getTime())); // set Request Initiated time
				
				AccountAdapter accountAdapter = new AccountAdapter();
				accountAdapter.setUrl( TppConstants.yapilyUrl + "/" + TppConstants.accountsUrl + "/" + accountData.getId() + "/" + TppConstants.periodicPaymentsUrl);
				accountAdapter.setToken(consentTokenDetails.getConsentToken());
				AdapterResponse adapterResponse = restAdapter.callAccountsDetailsApi(accountAdapter);
				
				loggingDetails.setRespArrivalDt(new Timestamp(new Date().getTime())); // set Response Arrival time
				
				if(!"200".equalsIgnoreCase(adapterResponse.getStatusCode())) {
					loggingDetails.setReqProcessingDt(new Timestamp(new Date().getTime()).getTime() - loggingDetails.getReqInitiatedDt().getTime() + ""); // set Request Processing time in ML
					standingOrders = loggingFetchingDetails(adapterResponse.getData(),loggingDetails,adapterResponse.getStatusCode(),adapterResponse.getStatusMsg(),adapterResponse.getStatusFlag());
					throw new Exception(adapterResponse.getStatusMsg());
				}
					
				List<StandingOrderData> standingOrderDataList = accountsResponseFormatter.formatStandingOrderDetails(adapterResponse.getData());
				
				for(StandingOrderData standingOrderData : standingOrderDataList)
				{
					if(daoApiRepository.saveStandingOrdersDetails(standingOrderData, accountData, consentTokenDetails))
						standingOrders = daoApiRepository.saveStandingOrdersIdentificationDetails(standingOrderData, accountData, consentTokenDetails);
				}
				
				loggingDetails.setReqProcessingDt(new Timestamp(new Date().getTime()).getTime() - loggingDetails.getReqInitiatedDt().getTime() + ""); // set Request Processing time in ML
				
				if(!standingOrders)
				{
					standingOrders = loggingFetchingDetails(adapterResponse.getData(),loggingDetails,TppConstants.failureCode,"StandingOrders Details Fetching Failed",TppConstants.failure);
					throw new Exception("StandingOrders Details Fetching Failed");
				}
			    standingOrders = loggingFetchingDetails(adapterResponse.getData(),loggingDetails,TppConstants.successCode,"StandingOrders Details Fetched Successfully",TppConstants.success);
			}
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
		return standingOrders;
	}

	@Override
	public UserResponse getInstitutions() {

		UserResponse userResponse = null;

		try {

			userResponse = new UserResponse();
			AccountAdapter accountAdapter = new AccountAdapter();
			accountAdapter.setUrl( TppConstants.yapilyUrl + "/" + TppConstants.institutionsUrl);
			
			AdapterResponse adapterResponse = restAdapter.callInstitutions(accountAdapter);
			
			if("200".equalsIgnoreCase(adapterResponse.getStatusCode())) {
				
				List<InstitutionData> institutionDataList = accountsResponseFormatter.formatInstitutionDetails(adapterResponse.getData());
				
				for(InstitutionData institutionData : institutionDataList)
				{
					for(int j=0 ; j<institutionData.getMedia().size() ; j++)
					{
						if(institutionData.getMedia().get(j).getType().equals("logo"))
							institutionData.setLogo(institutionData.getMedia().get(j).getSource());
						else if(institutionData.getMedia().get(j).getType().equals("icon"))
							institutionData.setIcon(institutionData.getMedia().get(j).getSource());
						
						daoApiRepository.saveInstitutionDetails(institutionData);
					}
				}

			}
			
			LOGGER.info(environment.getProperty("success.allinst.store.message"));
			userResponse.setRsCode(environment.getProperty("success.code"));
			userResponse.setRsMsg(environment.getProperty("success.allinst.store.message"));
			

		} catch (Exception e) {

			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
			throw new TechnicalDeclineException(environment.getProperty("exceptionmessage"),
					environment.getProperty("exceptioncode"));
		}
		return userResponse;
	}
	
	public boolean loggingFetchingDetails(String data, LoggingDetails loggingDetails, String successCode, String rspDesc, String txnStatus) {
		boolean result = false;
		try {
			String transactionId = null;
			String nine = "";
			int value;
			
			if(data != null)
			{
				String respDataSize = String.format("%.3f", (float) data.getBytes().length/(float)1000);
				loggingDetails.setRespDataSize(respDataSize); // set response data size
			}
			
			loggingDetails.setTransactionRrn(RRN.genRRN()); // set rrn
			
			transactionId = FSSOpenBankingRunner.transctionId;
			
			String incrementValue = transactionId.substring(3,transactionId.length());
			
			for(int i = 0;i<incrementValue.length();i++)
				nine +="9";
			
			if(incrementValue.contains(nine))
				value = incrementValue.length()+1;
			else
				value = incrementValue.length();
			
			String id = String.format("%0"+value+"d", Integer.parseInt(incrementValue) + 1);
			
			loggingDetails.setTransactionId(transactionId.substring(0,3) + id); // set txn id
			
			FSSOpenBankingRunner.transctionId = loggingDetails.getTransactionId();
			
			loggingDetails.setRespCode(successCode);      // set resp code
			loggingDetails.setRespDesc(rspDesc);      // set resp desc
			loggingDetails.setTransactionStatus(txnStatus);  // set txn status
			
			result = daoApiRepository.saveFetchingDetails(loggingDetails);
			
			if(!result)
				throw new Exception("Logging Failed");
			
			LOGGER.info("Logged Successfully");
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e.getMessage());
		}
	return result;
	}

}
