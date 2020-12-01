/**
 * 
 */
package com.fss.openbanking.responseformatter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.openbanking.bean.AccountBalances;
import com.fss.openbanking.bean.AccountData;
import com.fss.openbanking.bean.AuthenticationBean;
import com.fss.openbanking.bean.ConsentResponse;
import com.fss.openbanking.bean.CreateResponseData;
import com.fss.openbanking.bean.DirectDebitData;
import com.fss.openbanking.bean.InstitutionData;
import com.fss.openbanking.bean.StandingOrderData;
import com.fss.openbanking.bean.TransactionData;
import com.fss.openbanking.dao.DaoApiRepository;
import com.fss.openbanking.service.JSONResponseWrapper;
import com.google.gson.Gson;

/**
 * @author selvakumara
 *
 */
@Service("accountsResponseFormatter")
public class AccountsResponseFormatterImpl implements AccountsResponseFormatter {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AccountsResponseFormatterImpl.class);

	@Autowired
	private JSONResponseWrapper jsonResponseWrapper;
	
	@Autowired
	private DaoApiRepository daoApiRepository;
	
	@Override
	public List<AccountData> formatAccountsResponse(String data) {
		List<AccountData> accountsList = new ArrayList<AccountData>();
		try {
			JSONObject responseJSONObject = new JSONObject(data);
			AccountData accountData = new AccountData();
			
			if(responseJSONObject.has("data")) {
				
				JSONArray accountDataArray = new JSONArray(responseJSONObject.get("data").toString());
				
				for(int i = 0 ; i < accountDataArray.length() ; i++)
				{
					JSONObject accountDataObj = new JSONObject(accountDataArray.get(i).toString());
					
					accountData = jsonResponseWrapper.getAccountDataDetails(accountDataObj.toString());
					
					accountsList.add(accountData);
				}
				
			} else {
				LOGGER.info("Data Not Exists");
			}
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		LOGGER.info("Formatted List size is "+accountsList.size());
		return accountsList;
	}
	
	@Override
	public List<AccountBalances> formatBalanceDetails(String data) {
		List<AccountBalances> accountBalancesList = new ArrayList<>();
		try {
			JSONObject responseJSONObject = new JSONObject(data);
			AccountBalances accountBalances = new AccountBalances();
			
			if(responseJSONObject.has("data")) {
				
				JSONObject balanceDataObj = new JSONObject(responseJSONObject.get("data").toString());

				
				JSONArray balanceDataArray = new JSONArray(balanceDataObj.get("balances").toString());
				
				for(int i = 0 ; i < balanceDataArray.length() ; i++)
				{
					JSONObject balanceData = new JSONObject(balanceDataArray.get(i).toString());
					
					accountBalances = jsonResponseWrapper.getBalanceDataDetails(balanceData.toString());
					accountBalancesList.add(accountBalances);
				}
				
			} else {
				LOGGER.info("Data Not Exists");
			}
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);

		}
		return accountBalancesList;
	}
	
	@Override
	public List<TransactionData> formatTransactionDetails(String data) {
		List<TransactionData> transactionDataList = new ArrayList<TransactionData>();
		try {
			JSONObject responseJSONObject = new JSONObject(data);
			TransactionData transactionData = new TransactionData();
			
			if(responseJSONObject.has("data")) {
				
				JSONArray accountDataArray = new JSONArray(responseJSONObject.get("data").toString());
				
				for(int i = 0 ; i < accountDataArray.length() ; i++)
				{
					JSONObject accountDataObj = new JSONObject(accountDataArray.get(i).toString());
					
					transactionData = jsonResponseWrapper.getTxnDataDetails(accountDataObj.toString());
					
					transactionDataList.add(transactionData);
				}
				
			} else {
				LOGGER.info("Data Not Exists");
			}
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);

		}
		return transactionDataList;
	}

	
	@Override
	public List<DirectDebitData> formatDirectDebitDetails(String data) {
		List<DirectDebitData> directDebitDataList = new ArrayList<DirectDebitData>();
		try {
			JSONObject responseJSONObject = new JSONObject(data);
			DirectDebitData directDebitData = new DirectDebitData();
			
			if(responseJSONObject.has("data")) {
				
				JSONArray directDebitDataArray = new JSONArray(responseJSONObject.get("data").toString());
				
				for(int i = 0 ; i < directDebitDataArray.length() ; i++)
				{
					JSONObject directDebitObj = new JSONObject(directDebitDataArray.get(i).toString());
					
					directDebitData = jsonResponseWrapper.getAccountDirectDebitDetails(directDebitObj.toString());
					
					directDebitDataList.add(directDebitData);
				}
			}
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);

		}
		return directDebitDataList;
	}
	
	@Override
	public List<StandingOrderData> formatStandingOrderDetails(String data) {
		List<StandingOrderData> standingOrderDataList = new ArrayList<StandingOrderData>();	
		try {
			JSONObject responseJSONObject = new JSONObject(data);
			StandingOrderData standingOrderData = new StandingOrderData();
			
			if(responseJSONObject.has("data")) {
				
				JSONArray directDebitDataArray = new JSONArray(responseJSONObject.get("data").toString());
				
				for(int i = 0 ; i < directDebitDataArray.length() ; i++)
				{
					JSONObject directDebitObj = new JSONObject(directDebitDataArray.get(i).toString());
					
					standingOrderData = jsonResponseWrapper.getAccountStandingOrdersDetails(directDebitObj.toString());
					
					standingOrderDataList.add(standingOrderData);
				}
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);

		}
		return standingOrderDataList;
	}

	@Override
	public ConsentResponse formatConsentInitiationResponse(String data) {
		ConsentResponse consentResponse = new ConsentResponse();
		try {
			JSONObject responseJSONObject = new JSONObject(data);
			if(responseJSONObject.has("data")) {
				Gson gson = new Gson();
				JSONObject dataJSONObject = responseJSONObject.getJSONObject("data");
				consentResponse = gson.fromJson(dataJSONObject.toString(), ConsentResponse.class);
				LOGGER.info(consentResponse.toString());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return consentResponse;
	}

	@Override
	public List<InstitutionData> formatInstitutionDetails(String data) {
		List<InstitutionData> institutionDataList = new ArrayList<>();
		try {
			JSONObject responseJSONObject = new JSONObject(data);
			InstitutionData institutionData = new InstitutionData();
			
			if(responseJSONObject.has("data")) {
				
				JSONArray directDebitDataArray = new JSONArray(responseJSONObject.get("data").toString());
				
				for(int i = 0 ; i < directDebitDataArray.length() ; i++)
				{
					JSONObject directDebitObj = new JSONObject(directDebitDataArray.get(i).toString());
					
					institutionData = jsonResponseWrapper.getInstitutionsDetails(directDebitObj.toString());
					
					institutionDataList.add(institutionData);
				}
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return institutionDataList;
	}

	@Override
	public boolean formatCreateUserDetails(String data, AuthenticationBean authenticationBean) {
		boolean result = false;
		try {
			
			
				JSONObject responseJSONObject = new JSONObject(data.toString());
				
				CreateResponseData createResponseData = new CreateResponseData();
					
					
				createResponseData = jsonResponseWrapper.getCreateResponseDetails(responseJSONObject.toString());
					
			    result = daoApiRepository.saveCreateUserDetails(authenticationBean, createResponseData);
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return result;
	}

}
