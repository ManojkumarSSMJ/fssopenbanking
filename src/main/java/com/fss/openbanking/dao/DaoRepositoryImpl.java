/**
 * 
 */
package com.fss.openbanking.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fss.openbanking.bean.AccountData;
import com.fss.openbanking.bean.AccountIdentifications;
import com.fss.openbanking.bean.AccountNames;
import com.fss.openbanking.bean.AuthenticationBean;
import com.fss.openbanking.bean.ConsentDetails;
import com.fss.openbanking.bean.ConsentResponse;
import com.fss.openbanking.bean.ConsentTokenDetails;
import com.fss.openbanking.bean.DirectDebitDetails;
import com.fss.openbanking.bean.InstitutionDetails;
import com.fss.openbanking.bean.ShowConsent;
import com.fss.openbanking.bean.StandingOrderDetails;
import com.fss.openbanking.bean.TppAccountsRequest;
import com.fss.openbanking.bean.TppBalanceResponse;
import com.fss.openbanking.bean.TppConsentForm;
import com.fss.openbanking.bean.TransactionData;
import com.fss.openbanking.bean.UpdateDTDetails;
import com.fss.openbanking.bean.UserDetails;

/**
 * @author selvakumara
 *
 */
@Repository("daoRepository")
public class DaoRepositoryImpl implements DaoRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DaoRepositoryImpl.class);
	
	private ApplicationContext applicationContext;
	
	private NamedParameterJdbcTemplate bankNamedParameterJdbcTemplate;
	
	@Autowired
	private MessageSource messageSource;	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	@Override
	public List<UserDetails> fetchUserDetails() {
		List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
		try {
			final String sql = messageSource.getMessage("fetch.openbanking.user.details", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			userDetailsList = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(UserDetails.class));
		
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return userDetailsList;
	}

	@Override
	public List<InstitutionDetails> fetchTppInstDetails() {
		List<InstitutionDetails> instDetails = new ArrayList<InstitutionDetails>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.inst.details", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			instDetails = bankNamedParameterJdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(InstitutionDetails.class));
		
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return instDetails;
	}


	@Override
	public List<ConsentDetails> fetchInstFeatureDetailsByBankId(String bankId) {
		List<ConsentDetails> consentDetails = new ArrayList<ConsentDetails>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.inst.feature.details.by.bank", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("bankId", bankId);
			consentDetails = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(ConsentDetails.class));
		
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return consentDetails;
	}


	@Override
	public boolean deleteConsentTemp(TppConsentForm tppConsentForm) {
		boolean result = false;
		try {
			final String sql = messageSource.getMessage("delete.consent.details.by.bank", null, null);
			final String sql1 = messageSource.getMessage("delete.consent.feature.details.by.bank", null, null);
			LOGGER.info("Query 1 ::{}", sql);
			LOGGER.info("Query 2 ::{}", sql1);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("mobileNumber", tppConsentForm.getMobileNumber());
			paramMap.put("institutionId", tppConsentForm.getBankId());
			paramMap.put("consentId", tppConsentForm.getConsentId());
			paramMap.put("uuid", tppConsentForm.getUuId());

			int delTknRows = bankNamedParameterJdbcTemplate.update(sql, paramMap);
			if(delTknRows > 0) {
				int delTknFtrRows = bankNamedParameterJdbcTemplate.update(sql1, paramMap);
				if(delTknFtrRows > 0)
					result = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public InstitutionDetails fetchInstDetailsByBankId(String bankId) {
		InstitutionDetails instDetails = null;
		try {
			final String sql = messageSource.getMessage("fetch.bank.details.by.bank.id", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("bankId", bankId);
			instDetails = bankNamedParameterJdbcTemplate.queryForObject(sql,paramMap,BeanPropertyRowMapper.newInstance(InstitutionDetails.class));
		
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return instDetails;
	}


	@Override
	public List<ConsentTokenDetails> fetchConsentDetailsByUser(String mobileNumber) {
		List<ConsentTokenDetails> consentTokenDetails = new ArrayList<>();
		try {
			final String sql = messageSource.getMessage("consent.fetch.details.by.user.id", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("mobileNumber", mobileNumber);
			consentTokenDetails = bankNamedParameterJdbcTemplate.query(sql,paramMap,BeanPropertyRowMapper.newInstance(ConsentTokenDetails.class));
		
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return consentTokenDetails;
	}


	@Override
	public List<ConsentDetails> fetchConsentDetailsByBank(ConsentTokenDetails consentDetail) {
		List<ConsentDetails> consentDetails = new ArrayList<ConsentDetails>();
		try {
			final String sql = messageSource.getMessage("consent.fetch.consent.by.uuid", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("uuId", consentDetail.getUuId());
			paramMap.put("consentId", consentDetail.getConsentId());
			consentDetails = bankNamedParameterJdbcTemplate.query(sql,paramMap,BeanPropertyRowMapper.newInstance(ConsentDetails.class));
		    
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return consentDetails;
	}


	@Override
	public String getUuIdusingMobileNumber(String mobileNumber){
		String uuid = null;
		LOGGER.info("Get User Details from DB");
		try {
			
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("fetch.user.details.by.user.id", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  LOGGER.info("named Parameter Jdbc Template");
			  bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", mobileNumber);
			  uuid = bankNamedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
			  
			 }
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
			uuid = null;
		}
		return uuid;

	}


	@Override
	public boolean saveConsentTemp(ConsentResponse consentResponse) {
		boolean insertRows = false;
		LOGGER.info("Select Consent Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String qry = messageSource.getMessage("select.consent.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ qry); 
			  LOGGER.info("named Parameter Jdbc Template");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentResponse.getApplicationUserId());
			  namedParameters.put("institutionId", consentResponse.getInstitutionId());
			  namedParameters.put("consentId", consentResponse.getId());
			  int count = bankNamedParameterJdbcTemplate.queryForObject(qry, namedParameters, Integer.class);
			  LOGGER.info("Count :::: "+ count); 
			  if(count == 0)
			  { 
				  insertRows = updateConsentTemp(consentResponse, "1");
			  }
			  else
			  {
				  insertRows = updateConsentTemp(consentResponse , "2");
			  }
			  
			  if(insertRows)
			  {
				  deleteConsentFeatureDetails(consentResponse);
				  insertRows = saveConsentFeatureDetails(consentResponse,consentResponse.getFeatureScope());
			  }
				  
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;
	}
	
	
	public boolean updateConsentTemp(ConsentResponse consentResponse, String flag) {
		boolean insertRows = false;
		LOGGER.info("Update Consent Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String qry = null;
			  
			  if("1".equals(flag))
				  qry = messageSource.getMessage("insert.consent.details.by.bank", null, null);
			  else
				  qry = messageSource.getMessage("update.consent.details.by.bank", null, null);
				  
			  LOGGER.info("Query {}"+ qry); 
			  LOGGER.info("named Parameter Jdbc Template");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentResponse.getApplicationUserId());
			  namedParameters.put("institutionId", consentResponse.getInstitutionId());
			  namedParameters.put("uuid",consentResponse.getUserUuid());
			  namedParameters.put("consentId",consentResponse.getId());
			  namedParameters.put("status","Y");
			  namedParameters.put("expiryAt",consentResponse.getExpiresAt().split("T")[0] + " " +consentResponse.getExpiresAt().split("T")[1].substring(0,8));
			  int count = bankNamedParameterJdbcTemplate.update(qry, namedParameters);
			  LOGGER.info("Count :::: "+ count); 
			  if(count>0)
			  { 
				 insertRows=true; 
			  }
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;
	}


	private boolean saveConsentFeatureDetails(ConsentResponse consentResponse, String[] featureScope) {
		boolean insertRows = false;
		LOGGER.info("Select Consent Feature Details into DB");
		try {
			  LOGGER.info("try block");
			  int count = 0;
			  String qry = messageSource.getMessage("insert.consent.feature.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ qry); 
			  LOGGER.info("named Parameter Jdbc Template");
			  for(String feautureId : featureScope)
			  {
				  Map<String, Object> namedParameters = new HashMap<String, Object>();
				  namedParameters.put("featureId", feautureId);
				  namedParameters.put("consentId", consentResponse.getId());
				  namedParameters.put("uuid",consentResponse.getUserUuid());
				  namedParameters.put("status","Y");
				  count += bankNamedParameterJdbcTemplate.update(qry, namedParameters);
			  }
			  LOGGER.info("Count :::: "+ count); 
			  if(count>0)
				 insertRows=true;
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;
	}
	
	private boolean deleteConsentFeatureDetails(ConsentResponse consentResponse) {
		boolean insertRows = false;
		LOGGER.info("Delete Consent Feature Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String qry = messageSource.getMessage("delete.consent.feature.details.by.bank", null, null);			  
			  
			  LOGGER.info("Query {}"+ qry); 
			  LOGGER.info("named Parameter Jdbc Template");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("consentId", consentResponse.getId());
			  namedParameters.put("uuid",consentResponse.getUserUuid());
			  int count = bankNamedParameterJdbcTemplate.update(qry, namedParameters);
			  LOGGER.info("Count :::: "+ count); 
			  if(count>0)
				 insertRows=true;
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;
	}


	@Override
	public ConsentTokenDetails fetchConsentBankId(String bankId,String mobileNumber) {
		ConsentTokenDetails consentTokenDetails = null;
		try {
			String sql = messageSource.getMessage("select.tpp.user.consent.details.by.bank", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("bankId", bankId);
			paramMap.put("mobileNumber", mobileNumber);
			int count = bankNamedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
			if(count > 0)
			{
				sql = messageSource.getMessage("fetch.tpp.user.consent.details.by.bank", null, null);
				consentTokenDetails = bankNamedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(ConsentTokenDetails.class));
			}
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return consentTokenDetails;
	}

	@Override
	public List<TppBalanceResponse> fetchBalanceDetailsByBankId(TppAccountsRequest tppAccountsRequest, String userId) {
		List<TppBalanceResponse> tppBalanceResponse = null;
		try {
			final String sql = messageSource.getMessage("fetch.tpp.balance.details.by.bank", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("bankId", tppAccountsRequest.getBankId());
			paramMap.put("accountId", tppAccountsRequest.getAccountId());
			paramMap.put("mobileNumber", tppAccountsRequest.getMobileNumber());
			tppBalanceResponse = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(TppBalanceResponse.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return tppBalanceResponse;
	}


	@Override
	public List<AccountData> fetchAccountDetailsByUserId(String mobileNumber) {
		 List<AccountData> accountData = new ArrayList<AccountData>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.account.details.by.user", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("mobileNumber", mobileNumber);
			accountData = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(AccountData.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return accountData;
	}


	@Override
	public List<AccountIdentifications> fetchAccountIdentificationsDetails(AccountData accountDetail) {
		 List<AccountIdentifications> accountIdentifications = new ArrayList<AccountIdentifications>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.account.identification.details", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("bankId", accountDetail.getBankId());
			paramMap.put("mobileNumber", accountDetail.getMobileNumber());
			paramMap.put("accountId", accountDetail.getId());
			accountIdentifications = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(AccountIdentifications.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return accountIdentifications;
	}

	@Override
	public List<AccountNames> fetchAccountNamesDetails(AccountData accountDetail) {
		 List<AccountNames> accountNames = new ArrayList<AccountNames>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.account.names.details", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("bankId", accountDetail.getBankId());
			paramMap.put("mobileNumber", accountDetail.getMobileNumber());
			paramMap.put("accountId", accountDetail.getId());
			accountNames = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(AccountNames.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return accountNames;
	}
	
	@Override
	public boolean saveTokenDetails(ConsentResponse consentResponse, String token) {
		boolean insertRows = false;
		LOGGER.info("Insert Token Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String qry = messageSource.getMessage("update.token.details.in.tokenmaster", null, null);
			  LOGGER.info("Query {}"+ qry); 
			  LOGGER.info("named Parameter Jdbc Template");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentResponse.getApplicationUserId());
			  namedParameters.put("bankId", consentResponse.getInstitutionId());
			  namedParameters.put("uuId", consentResponse.getUserUuid());
			  namedParameters.put("consentId", consentResponse.getId());
			  namedParameters.put("token",token);
			  int count = bankNamedParameterJdbcTemplate.update(qry, namedParameters);
			  LOGGER.info("Count :::: "+ count); 
			  if(count>0)
			  { 
				  qry = messageSource.getMessage("update.token.details.in.tokefeature.link", null, null);
				  LOGGER.info("Query {}"+ qry); 
				  int count1 = bankNamedParameterJdbcTemplate.update(qry, namedParameters);
				  
				  if(count1>0)
					  insertRows=true; 
			  }
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;
	}


	@Override
	public List<TransactionData> fetchTransactionDetails(UserDetails userDetails, TppAccountsRequest tppAccountsRequest) {
		 List<TransactionData> transactionData = new ArrayList<TransactionData>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.transaction.details.by.bank", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("bankId", tppAccountsRequest.getBankId());
			paramMap.put("accountId", tppAccountsRequest.getAccountId());
			paramMap.put("mobileNumber", tppAccountsRequest.getMobileNumber());
			transactionData = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(TransactionData.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return transactionData;
	}
	
	@Override
	public List<TransactionData> fetchAllTransactionDetails(UserDetails userDetails) {
		 List<TransactionData> transactionData = new ArrayList<TransactionData>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.transaction.details.all", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("mobileNumber", userDetails.getMobileNumber());
			transactionData = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(TransactionData.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return transactionData;
	}

	@Override
	public List<DirectDebitDetails> fetchDirectDebitsDetails(UserDetails userDetails, TppAccountsRequest tppAccountsRequest) {
		 List<DirectDebitDetails> directDebitDetails = new ArrayList<>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.directdebits.details.by.bank", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("bankId", tppAccountsRequest.getBankId());
			paramMap.put("accountId", tppAccountsRequest.getAccountId());
			paramMap.put("mobileNumber", tppAccountsRequest.getMobileNumber());
			directDebitDetails = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(DirectDebitDetails.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return directDebitDetails;
	}

	@Override
	public List<DirectDebitDetails> fetchAllDirectDebitsDetails(UserDetails userDetails) {
		 List<DirectDebitDetails> directDebitDetails = new ArrayList<DirectDebitDetails>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.directdebits.details.all", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("mobileNumber", userDetails.getMobileNumber());
			directDebitDetails = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(DirectDebitDetails.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return directDebitDetails;
	}
	
	@Override
	public List<StandingOrderDetails> fetchStandingOrdersDetails(UserDetails userDetails, TppAccountsRequest tppAccountsRequest) {
		 List<StandingOrderDetails> standingOrderDetails = new ArrayList<StandingOrderDetails>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.standingorders.details.by.bank", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("bankId", tppAccountsRequest.getBankId());
			paramMap.put("accountId", tppAccountsRequest.getAccountId());
			paramMap.put("mobileNumber", tppAccountsRequest.getMobileNumber());
			standingOrderDetails = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(StandingOrderDetails.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return standingOrderDetails;
	}

	@Override
	public List<StandingOrderDetails> fetchAllStandingOrdersDetails(UserDetails userDetails) {
		 List<StandingOrderDetails> standingOrderDetails = new ArrayList<>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.standingorders.details.all", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("mobileNumber", userDetails.getMobileNumber());
			standingOrderDetails = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(StandingOrderDetails.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return standingOrderDetails;
	}
	
	@Override
	public List<AccountIdentifications> fetchStandingOrdersIdentificationsDetails(StandingOrderDetails standingOrderDetail) {
		 List<AccountIdentifications> accountIdentifications = new ArrayList<>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.standingorders.identification.details.by.bank", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("bankId", standingOrderDetail.getBankId());
			paramMap.put("mobileNumber", standingOrderDetail.getMobileNumber());
			paramMap.put("accountId", standingOrderDetail.getAccountId());
			paramMap.put("soId", standingOrderDetail.getStandingOrderId());
			accountIdentifications = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(AccountIdentifications.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return accountIdentifications;
	}


	@Override
	public String fetchLastUpdateDateTime(String mobileNumber, String bankId, String bankName) {
		 String LastDt = null;
		try {
		    String sql = messageSource.getMessage("select.last.update.datetime", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("mobileNumber", mobileNumber);
			paramMap.put("bankId", bankId);
			paramMap.put("bankName", bankName);
			int count = bankNamedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);		  
			LOGGER.info("Count :::: "+ count); 
		    if(count>0)
		    { 
		    	sql = messageSource.getMessage("fetch.last.update.datetime", null, null);
		    	LastDt = bankNamedParameterJdbcTemplate.queryForObject(sql, paramMap, String.class);
			}
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
			LastDt = null;
		}
		return LastDt;
	}


	@Override
	public List<AccountData> fetchAccountDetailsByBank(String bankId, String mobileNumber) {
		 List<AccountData> accountData = new ArrayList<AccountData>();
		try {
			final String sql = messageSource.getMessage("fetch.tpp.account.details.by.bank", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("bankId", bankId);
			paramMap.put("mobileNumber", mobileNumber);
			accountData = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(AccountData.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return accountData;
	}


	@Override
	public String fetchUpdateHoursfromInstitutions(ConsentTokenDetails consentTokenDetails) {
		String updHrs = null;
		LOGGER.info("Get Institution Update Hours Details from DB");
		try {
			
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("fetch.tpp.inst.update.hours.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  LOGGER.info("named Parameter Jdbc Template");
			  bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("bankName", consentTokenDetails.getBankName());
			  updHrs = bankNamedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
			  
			 }
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
			updHrs = null;
		}
		return updHrs;

	}


	@Override
	public int tppCreateAccount(AuthenticationBean authenticationBean) {
		int insertRows = 0;
		LOGGER.info("Select Account Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.user.account.details", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("role", authenticationBean.getRole());
			  namedParameters.put("customerName", authenticationBean.getCustomerName());
			  namedParameters.put("mobileNumber", authenticationBean.getMobileNumber());
			  namedParameters.put("userId", authenticationBean.getUserId());
			  namedParameters.put("password", authenticationBean.getEncodedPassword());
			  namedParameters.put("activeStatus", "Y");
			  namedParameters.put("passwordStatus", "Y");
			  int count = bankNamedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
			  LOGGER.info("Count :::: "+ count); 
			  if(count == 0)
			  { 
				  sql = messageSource.getMessage("insert.user.account.details", null, null);
				  count = bankNamedParameterJdbcTemplate.update(sql, namedParameters);
				  LOGGER.info("Count :::: "+ count); 
				  if(count>0)
				  { 
					  insertRows = 1; 
				  }
			  }
			  else
			  {
				  insertRows = 2;
			  } 
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;
	}


	@Override
	public List<UpdateDTDetails> fetchUpdatedDetails(UserDetails userDetails) {
		 List<UpdateDTDetails> updateDTDetails = new ArrayList<UpdateDTDetails>();
		try {
			final String sql = messageSource.getMessage("select.update.details.by.mobilenumber", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("mobileNumber", userDetails.getMobileNumber());
			updateDTDetails = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(UpdateDTDetails.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return updateDTDetails;
	}


	@Override
	public List<ShowConsent> fetchAllConsentDetails() {
		List<ShowConsent> showConsent = new ArrayList<ShowConsent>();
		try {
			final String sql = messageSource.getMessage("fetch.all.consent.details", null, null);
			LOGGER.info("Query::{}", sql);
			bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			showConsent = bankNamedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(ShowConsent.class));
		} catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return showConsent;
	}


	@Override
	public int fetchTransactionStatusDetails(String transactinStatus) {
		int count = 0;
		LOGGER.info("Select Txn Status Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.txn.status.details", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("transactinStatus", transactinStatus);
			  count = bankNamedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
			  LOGGER.info("Count :::: "+ count); 
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return count;
	}


	@Override
	public int fetchTransactionStatusDetailsbyMoths(String month, int year, String transactinStatus) {
		int count = 0;
		LOGGER.info("Select Txn Status Details by Month from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.txn.status.details.by.month", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("month", month);
			  namedParameters.put("year", year);
			  namedParameters.put("transactinStatus", transactinStatus);
			  count = bankNamedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
			  LOGGER.info("Count :::: "+ count); 
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return count;
	}


	@Override
	public int fetchBankConsentDetailsbyUser(InstitutionDetails institutionDetails, UserDetails userDetails) {
		int count = 0;
		LOGGER.info("Select Txn Status Details by Month from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.bank.consent.details.by.user", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("bankId", institutionDetails.getBankId());
			  namedParameters.put("mobileNumber", userDetails.getMobileNumber());
			  count = bankNamedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
			  LOGGER.info("Count :::: "+ count); 
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return count;
	}


	@Override
	public int fetchBankConsentDetailsbyBank(InstitutionDetails institutionDetails) {
		int count = 0;
		LOGGER.info("Select Txn Status Details by Month from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.bank.consent.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  bankNamedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("bankId", institutionDetails.getBankId());
			  count = bankNamedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
			  LOGGER.info("Count :::: "+ count); 
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return count;
	}

}
