/**
 * 
 */
package com.fss.openbanking.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fss.openbanking.bean.AccountBalances;
import com.fss.openbanking.bean.AccountData;
import com.fss.openbanking.bean.AccountIdentifications;
import com.fss.openbanking.bean.AccountNames;
import com.fss.openbanking.bean.AuthenticationBean;
import com.fss.openbanking.bean.ConsentTokenDetails;
import com.fss.openbanking.bean.Countries;
import com.fss.openbanking.bean.CreateResponseData;
import com.fss.openbanking.bean.CreditLines;
import com.fss.openbanking.bean.DirectDebitData;
import com.fss.openbanking.bean.InstitutionData;
import com.fss.openbanking.bean.LoggingDetails;
import com.fss.openbanking.bean.StandingOrderData;
import com.fss.openbanking.bean.TransactionData;

/**
 * @author selvakumara
 *
 */
@Repository("DaoApiRepository")
public class DaoApiRepositoryImpl implements DaoApiRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DaoApiRepositoryImpl.class);
	
	private ApplicationContext applicationContext;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@Override
	public boolean saveAccountDetails(ConsentTokenDetails consentTokenDetails, AccountData accountData) {
		boolean insertRows = false;
		LOGGER.info("Select Account Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.account.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", accountData.getId());
			  int count = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
			  LOGGER.info("Count :::: "+ count); 
			  if(count == 0)
			  { 
				  insertRows = updateAccountDetails(consentTokenDetails, accountData, "1");
			  }
			  else
			  {
				  insertRows = updateAccountDetails(consentTokenDetails, accountData , "2");
			  } 
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;
	}
	
	public boolean updateAccountDetails(ConsentTokenDetails consentTokenDetails, AccountData data,String flag) {
		boolean insertRows = false;
		LOGGER.info("Insert Account Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = null;
			  
			  if("1".equals(flag))
				  sql = messageSource.getMessage("insert.account.details.by.bank", null, null);
			  else
				  sql = messageSource.getMessage("update.account.details.by.bank", null, null);
			  
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", data.getId());
			  namedParameters.put("accType", data.getType());
			  namedParameters.put("accCrncy", data.getCurrency());
			  namedParameters.put("accUsgType", data.getUsageType());
			  namedParameters.put("accSType", data.getAccountType());
			  namedParameters.put("accNckName", data.getNickname());
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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
	

	@Override
	public boolean saveAccountNameDetails(ConsentTokenDetails consentTokenDetails, AccountData accountData) {
		boolean insertRows = false;
		LOGGER.info("Select Account Name Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.account.name.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  for(AccountNames accountNames : accountData.getAccountNames())
				{
				  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
				  Map<String, Object> namedParameters = new HashMap<String, Object>();
				  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
				  namedParameters.put("bankId", consentTokenDetails.getBankId());
				  namedParameters.put("accId", accountData.getId());
				  namedParameters.put("accName", accountNames.getName());
				  int count = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
				  LOGGER.info("Count :::: "+ count); 
				  if(count == 0)
				  { 
					  insertRows = updateAccountNameDetails(consentTokenDetails, accountNames, accountData, "1");
				  }
				  else
				  {
					  insertRows = updateAccountNameDetails(consentTokenDetails, accountNames, accountData , "2");
				  } 
				}
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;

	}
	
	public boolean updateAccountNameDetails(ConsentTokenDetails consentTokenDetails, AccountNames accountNames, AccountData accountData,
			String flag) {
		boolean insertRows = false;
		LOGGER.info("Update Account Name Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = null;
			  
			  if("1".equals(flag))
				  sql = messageSource.getMessage("insert.account.name.details.by.bank", null, null);
			  else
				  sql = messageSource.getMessage("update.account.name.details.by.bank", null, null);
			  
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", accountData.getId());
			  namedParameters.put("accName", accountNames.getName());
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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
	public boolean saveAccountIdentificationsDetails(ConsentTokenDetails consentTokenDetails, AccountData accountData) {
		boolean insertRows = false;
		LOGGER.info("Select Account Identification Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.account.identification.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  for(AccountIdentifications accountIdentifications : accountData.getAccountIdentifications())
				{
				  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
				  Map<String, Object> namedParameters = new HashMap<String, Object>();
				  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
				  namedParameters.put("bankId", consentTokenDetails.getBankId());
				  namedParameters.put("accId", accountData.getId());
				  namedParameters.put("accIdfnType", accountIdentifications.getType());
				  namedParameters.put("accIdfnValue", accountIdentifications.getIdentification());
				  int count = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
				  LOGGER.info("Count :::: "+ count); 
				  if(count == 0)
				  { 
					  insertRows = updateAccountIdentificationsDetails(consentTokenDetails, accountIdentifications, accountData, "1");
				  }
				  else
				  {
					  insertRows = updateAccountIdentificationsDetails(consentTokenDetails, accountIdentifications, accountData , "2");
				  }
				}
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;
	}
	
	public boolean updateAccountIdentificationsDetails(ConsentTokenDetails consentTokenDetails, AccountIdentifications accountIdentifications, AccountData accountData, String flag) {
		boolean insertRows = false;
		LOGGER.info("Update Account Identification Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String sql= null;
			  
			  if("1".equals(flag))
				  sql = messageSource.getMessage("insert.account.identification.details.by.bank", null, null);
			  else
				  sql = messageSource.getMessage("update.account.identification.details.by.bank", null, null);
			  
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", accountData.getId());
			  namedParameters.put("accIdfnType", accountIdentifications.getType());
			  namedParameters.put("accIdfnValue", accountIdentifications.getIdentification());
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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

	@Override
	public boolean saveAccountBalanceDetails(ConsentTokenDetails consentTokenDetails,AccountData accountData) {
		boolean insertRows = false;
		LOGGER.info("Select Balance Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.account.balances.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  for(AccountBalances accountBalances : accountData.getAccountBalances())
				{
				  Map<String, Object> namedParameters = new HashMap<String, Object>();
				  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
				  namedParameters.put("bankId", consentTokenDetails.getBankId());
				  namedParameters.put("accId", accountData.getId());
				  namedParameters.put("blceType", accountBalances.getType());
				  int count = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
				  LOGGER.info("Count :::: "+ count); 
				  if(count == 0)
				  { 
					  insertRows = updateAccountBalancesDetails(consentTokenDetails, accountBalances, accountData, "1");
				  }
				  else
				  {
					  insertRows = updateAccountBalancesDetails(consentTokenDetails, accountBalances, accountData , "2");
				  }
				  
				  if("true".equals(accountBalances.getCreditLineIncluded()))
				  {
					  sql = messageSource.getMessage("select.account.balances.credit.details.by.bank", null, null);
					  LOGGER.info("Query {}"+ sql); 
					  for(CreditLines creditLines : accountBalances.getCreditLines())
					  {
						  namedParameters = new HashMap<String, Object>();
						  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
						  namedParameters.put("bankId", consentTokenDetails.getBankId());
						  namedParameters.put("accId", accountData.getId());
						  namedParameters.put("blceType", accountBalances.getType());
						  namedParameters.put("creditType", creditLines.getType());
						  
						  int count1 = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
						  LOGGER.info("Count :::: "+ count); 
						  if(count1 == 0)
						  { 
							  insertRows = updateBalancesCreditLineDetails(consentTokenDetails, accountBalances, creditLines, accountData, "1");
						  }
						  else
						  {
							  insertRows = updateBalancesCreditLineDetails(consentTokenDetails, accountBalances, creditLines, accountData , "2");
						  }
					  }
				  }
				}
			 }
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;

	}

	private boolean updateAccountBalancesDetails(ConsentTokenDetails consentTokenDetails, AccountBalances accountBalances, AccountData accountData, String flag) {
		boolean insertRows = false;
		LOGGER.info("Update Account Balance Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = null;
			  
			  if("1".equals(flag))
				  sql = messageSource.getMessage("insert.account.balances.details.by.bank", null, null);
			  else
				  sql = messageSource.getMessage("update.account.balances.details.by.bank", null, null);
			  
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", accountData.getId());
			  namedParameters.put("blceType", accountBalances.getType());
			  namedParameters.put("blceDt", accountBalances.getDateTime());
			  namedParameters.put("balance", accountBalances.getBalanceAmount().getAmount());
			  namedParameters.put("currency", accountBalances.getBalanceAmount().getCurrency());
			  namedParameters.put("crdtIncl", accountBalances.getCreditLineIncluded());
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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
	
	private boolean updateBalancesCreditLineDetails(ConsentTokenDetails consentTokenDetails,AccountBalances accountBalances, CreditLines creditLines, AccountData accountData, String flag) {
		boolean insertRows = false;
		LOGGER.info("Select Balance Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = null;
			  
			  if("1".equals(flag))
				  sql = messageSource.getMessage("insert.account.balances.credit.details.by.bank", null, null);
			  else
				  sql = messageSource.getMessage("update.account.balances.credit.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", accountData.getId());
			  namedParameters.put("blceType", accountBalances.getType());
			  namedParameters.put("creditType", creditLines.getType());
			  namedParameters.put("creditAmount", creditLines.getCreditLineAmount().getAmount());
			  namedParameters.put("creditCurrency", creditLines.getCreditLineAmount().getCurrency());
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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
	
	@Override
	public boolean saveTransactionDetails(TransactionData transactionData, AccountData accountData, ConsentTokenDetails consentTokenDetails) {
		boolean insertRows = false;
		LOGGER.info("Select Transaction Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.account.transaction.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", accountData.getId());
			  namedParameters.put("transactionId", transactionData.getId() !=null ? transactionData.getId() : transactionData.getEnrichment().getTransactionHash().getHash());
			  int count = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
			  if(count == 0)
			  { 
				  insertRows = updateTransactionDetails(transactionData, accountData, consentTokenDetails , "1");
			  }
			  else
			  {
				  insertRows = updateTransactionDetails(transactionData, accountData, consentTokenDetails , "2");
			  }
			  
			 }
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;

	}

	private boolean updateTransactionDetails(TransactionData transactionData, AccountData accountData,
			ConsentTokenDetails consentTokenDetails, String flag) {
		boolean insertRows = false;
		LOGGER.info("Update Transaction Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = null;
			  
			  if("1".equals(flag))
				  sql = messageSource.getMessage("insert.account.transaction.details.by.bank", null, null);
			  else
				  sql = messageSource.getMessage("update.account.transaction.details.by.bank", null, null);
			  
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", accountData.getId());
			  namedParameters.put("transactionId", transactionData.getId() !=null ? transactionData.getId() : transactionData.getEnrichment().getTransactionHash().getHash());
			  namedParameters.put("reference", transactionData.getReference());
			  namedParameters.put("txnDate", transactionData.getDate());
			  namedParameters.put("bokkingDt", transactionData.getBookingDateTime());
			  namedParameters.put("txnSts", transactionData.getStatus());
			  namedParameters.put("txnAmt", transactionData.getTransactionAmount().getAmount());
			  namedParameters.put("txnCrncy", transactionData.getTransactionAmount().getCurrency());
			  namedParameters.put("txnDesc", transactionData.getDescription());
			  namedParameters.put("txnCode", transactionData.getProprietaryBankTransactionCode().getCode());
			  namedParameters.put("txnIssuer", transactionData.getProprietaryBankTransactionCode().getIssuer());
			  namedParameters.put("blceType", transactionData.getBalance() !=null ? transactionData.getBalance().getType() : null);
			  namedParameters.put("blceAmnt", transactionData.getBalance() !=null ? transactionData.getBalance().getBalanceAmount().getAmount() : null);
			  namedParameters.put("blceCurcy", transactionData.getBalance() !=null ? transactionData.getBalance().getBalanceAmount().getCurrency() : null);
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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

	@Override
	public boolean saveDirectDebitDetails(DirectDebitData directDebitData, AccountData accountData, ConsentTokenDetails consentTokenDetails) {
		boolean insertRows = false;
		LOGGER.info("Select DirectDebits Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.account.directdebit.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", accountData.getId());
			  namedParameters.put("directDebitId", directDebitData.getId());
			  int count = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
			  if(count == 0)
			  { 
				  insertRows = updateDirectDebitDetails(directDebitData, accountData, consentTokenDetails , "1");
			  }
			  else
			  {
				  insertRows = updateDirectDebitDetails(directDebitData, accountData, consentTokenDetails , "2");
			  }
			  
			 }
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;

	}

	private boolean updateDirectDebitDetails(DirectDebitData directDebitData, AccountData accountData,ConsentTokenDetails consentTokenDetails, String flag) {
		boolean insertRows = false;
		LOGGER.info("Update DirectDebits Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = "";
			  
			  if("1".equals(flag))
				  sql = messageSource.getMessage("insert.account.directdebit.details.by.bank", null, null);
			  else
				  sql = messageSource.getMessage("update.account.directdebit.details.by.bank", null, null);
			  
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", accountData.getId());
			  namedParameters.put("directDebitId", directDebitData.getId());
			  namedParameters.put("directDebitStatus", directDebitData.getStatusDetails().getStatus());
			  namedParameters.put("payeeName", directDebitData.getPayeeDetails().getName());
			  namedParameters.put("reference", directDebitData.getReference());
			  namedParameters.put("prevPayAmount", directDebitData.getPreviousPaymentAmount().getAmount());
			  namedParameters.put("prevPayCurrency", directDebitData.getPreviousPaymentAmount().getCurrency());
			  namedParameters.put("prevPayDT", directDebitData.getPreviousPaymentDateTime());
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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

	@Override
	public boolean saveStandingOrdersDetails(StandingOrderData standingOrderData, AccountData accountData, ConsentTokenDetails consentTokenDetails) {
		boolean insertRows = false;
		LOGGER.info("Select StandingOrders Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.account.standingorder.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", accountData.getId());
			  namedParameters.put("standingOrderId", standingOrderData.getId());
			  int count = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
			  if(count == 0)
			  { 
				  insertRows = updateStandingOrdersDetails(standingOrderData, accountData, consentTokenDetails , "1");
			  }
			  else
			  {
				  insertRows = updateStandingOrdersDetails(standingOrderData, accountData, consentTokenDetails , "2");
			  }
			  
			 }
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;
	}

	private boolean updateStandingOrdersDetails(StandingOrderData standingOrderData, AccountData accountData, ConsentTokenDetails consentTokenDetails, String flag) {
		boolean insertRows = false;
		LOGGER.info("Update StandingOrders Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = "";
			  
			  if("1".equals(flag))
				  sql = messageSource.getMessage("insert.account.standingorder.details.by.bank", null, null);
			  else
				  sql = messageSource.getMessage("update.account.standingorder.details.by.bank", null, null);
			  
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", accountData.getId());
			  namedParameters.put("standingOrderId", standingOrderData.getId());
			  namedParameters.put("standingOrderStatus", standingOrderData.getStatusDetails().getStatus());
			  namedParameters.put("payeeName", standingOrderData.getPayeeDetails().getName());
			  namedParameters.put("reference", standingOrderData.getReference());
			  namedParameters.put("firstPayAmount", standingOrderData.getFirstPaymentAmount().getAmount());
			  namedParameters.put("firstPayCurrency", standingOrderData.getFirstPaymentAmount().getCurrency());
			  namedParameters.put("firstPayDT", standingOrderData.getFirstPaymentDateTime());
			  namedParameters.put("nextPayAmount", standingOrderData.getNextPaymentAmount() != null ? standingOrderData.getNextPaymentAmount().getAmount() : null);
			  namedParameters.put("nextPayCurrency", standingOrderData.getNextPaymentAmount() != null ? standingOrderData.getNextPaymentAmount().getCurrency() : null);
			  namedParameters.put("nextPayDT", standingOrderData.getNextPaymentDateTime());
			  namedParameters.put("finalPayAmount", standingOrderData.getFinalPaymentAmount() != null ? standingOrderData.getFinalPaymentAmount().getAmount() : null);
			  namedParameters.put("finalPayCurrency", standingOrderData.getFinalPaymentAmount() != null ? standingOrderData.getFinalPaymentAmount().getCurrency() : null);
			  namedParameters.put("finalPayDT", standingOrderData.getNextPaymentDateTime());
			  namedParameters.put("freqType", standingOrderData.getFrequency().getFrequencyType());
			  namedParameters.put("freqIntrnWeek", standingOrderData.getFrequency().getIntervalWeek());
			  namedParameters.put("freqExectDay", standingOrderData.getFrequency().getExecutionDay());
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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

	@Override
	public boolean saveStandingOrdersIdentificationDetails(StandingOrderData standingOrderData, AccountData accountData,ConsentTokenDetails consentTokenDetails) {
		boolean insertRows = false;
		LOGGER.info("Select StandingOrders Identification Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.standingorder.identification.details.by.bank", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  for(AccountIdentifications accountIdentifications : accountData.getAccountIdentifications())
				{
				  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
				  Map<String, Object> namedParameters = new HashMap<String, Object>();
				  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
				  namedParameters.put("bankId", consentTokenDetails.getBankId());
				  namedParameters.put("accId", accountData.getId());
				  namedParameters.put("soId", standingOrderData.getId());
				  int count = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
				  LOGGER.info("Count :::: "+ count); 
				  if(count == 0)
				  { 
					  insertRows = updateStandingOrdersIdentificationsDetails(standingOrderData, consentTokenDetails, accountIdentifications, accountData, "1");
				  }
				  else
				  {
					  insertRows = updateStandingOrdersIdentificationsDetails(standingOrderData, consentTokenDetails, accountIdentifications, accountData , "2");
				  }
				}
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;
	}

	private boolean updateStandingOrdersIdentificationsDetails(StandingOrderData standingOrderData, ConsentTokenDetails consentTokenDetails, AccountIdentifications accountIdentifications, AccountData accountData, String flag) {
		boolean insertRows = false;
		LOGGER.info("Update StandingOrders Identification Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String sql= null;
			  
			  if("1".equals(flag))
				  sql = messageSource.getMessage("insert.standingorder.identification.details.by.bank", null, null);
			  else
				  sql = messageSource.getMessage("update.standingorder.identification.details.by.bank", null, null);
			  
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("accId", accountData.getId());
			  namedParameters.put("soId", standingOrderData.getId());
			  namedParameters.put("soIdfnType", accountIdentifications.getType());
			  namedParameters.put("soIdfnValue", accountIdentifications.getIdentification());
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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

	@Override
	public boolean saveLastUpdatedDateTime(ConsentTokenDetails consentTokenDetails) {
		boolean insertRows = false;
		LOGGER.info("Update LastUpdate Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("insert.last.update.details", null, null);
			  
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", consentTokenDetails.getMobileNumber());
			  namedParameters.put("bankId", consentTokenDetails.getBankId());
			  namedParameters.put("bankName", consentTokenDetails.getBankName());
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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

	@Override
	public boolean saveInstitutionDetails(InstitutionData instData) {
		boolean insertRows = false;
		LOGGER.info("Insert Institution Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.institution.details.as.bank", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  LOGGER.info("named Parameter Jdbc Template");
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("bankId", instData.getId());
			  namedParameters.put("bankName", instData.getName());
			  int count = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
			  if(count == 0)
			  { 
				  insertRows = updateInstitutionDetails(instData, "1");
			  }
			  else
			  {
				  insertRows = updateInstitutionDetails(instData, "2");
			  }
			  
			  if(insertRows)
			  {
			  	  deleteInstitutionFtrDetails(instData);
				  insertRows = saveInstitutionFtrDetails(instData);
			  }
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;
	}
	
	public boolean updateInstitutionDetails(InstitutionData instData, String flag) {
		boolean insertRows = false;
		LOGGER.info("Update Institution Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = null;
			  String country = "";
			  
			  if("1".equals(flag))
				  sql = messageSource.getMessage("insert.institution.details.as.bank", null, null);
			  else
				  sql = messageSource.getMessage("update.institution.details.as.bank", null, null);
			  
			  for(Countries countries : instData.getCountries())
				  country += countries.getDisplayName() + ",";
			  
			  LOGGER.info("Query {}"+ sql); 
			  LOGGER.info("named Parameter Jdbc Template");
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("bankId", instData.getId());
			  namedParameters.put("bankName", instData.getName());
			  namedParameters.put("bankFName", instData.getFullName());
			  namedParameters.put("bankCnry", country.substring(0,country.length()-1));
			  namedParameters.put("bankLogo", instData.getLogo());
			  namedParameters.put("bankIcon", instData.getIcon());
			  namedParameters.put("updateHours", "4");
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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
	
	public boolean saveInstitutionFtrDetails(InstitutionData instData) {
		boolean insertRows = false;
		LOGGER.info("Insert Institution feature Details into DB");
		try {
			  LOGGER.info("try block"); 
			  int count = 0;
			  String sql = messageSource.getMessage("insert.institution.feature.details", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  LOGGER.info("named Parameter Jdbc Template");
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  for(String features : instData.getFeatures())
			  {
				  Map<String, Object> namedParameters = new HashMap<String, Object>();
				  namedParameters.put("bankId", instData.getId());
				  namedParameters.put("ftrId", features);
				  namedParameters.put("ftrName", features.replaceAll("_", " "));
				  count += namedParameterJdbcTemplate.update(sql, namedParameters);
			  }
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
	
	private boolean deleteInstitutionFtrDetails(InstitutionData instData) {
		boolean insertRows = false;
		LOGGER.info("delete Institution feature Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("delete.institution.feature.details", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  LOGGER.info("named Parameter Jdbc Template");
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("bankId", instData.getId());
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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

	@Override
	public boolean saveCreateUserDetails(AuthenticationBean authenticationBean, CreateResponseData createResponseData) {
		boolean insertRows = false;
		LOGGER.info("Select User Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.user.yapily.details", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", createResponseData.getApplicationUserId());
			  namedParameters.put("uuid", createResponseData.getUuid());
			  int count = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
			  if(count == 0)
			  { 
				  insertRows = updateCreateUserDetails(authenticationBean, createResponseData, "1");
			  }
			  else
			  {
				  insertRows = updateCreateUserDetails(authenticationBean, createResponseData, "2");
			  }
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		}
		return insertRows;

	}
	
	public boolean updateCreateUserDetails(AuthenticationBean authenticationBean, CreateResponseData createResponseData, String flag) {
		boolean insertRows = false;
		LOGGER.info("Update User Details into DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = null;
			  
			  if("1".equals(flag))
				  sql = messageSource.getMessage("insert.user.yapily.details", null, null);
			  else
				  sql = messageSource.getMessage("update.user.yapily.details", null, null);
			  
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("mobileNumber", createResponseData.getApplicationUserId());
			  namedParameters.put("uuid", createResponseData.getUuid());
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
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

	@Override
	public String fetchTransactionId() {
		String transactionId = null;
		LOGGER.info("Select User Details from DB");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("select.transaction.id.details", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  int count = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
			  if(count > 0)
			  { 
				  sql = messageSource.getMessage("fetch.transaction.id.details", null, null);
				  
				  transactionId = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
			  }
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
			transactionId = null;
		  }
		return transactionId;

	}

	@Override
	public boolean saveFetchingDetails(LoggingDetails loggingDetails) {
		boolean insertRows = false;
		LOGGER.info("insert Txn Details");
		try {
			  LOGGER.info("try block"); 
			  String sql = messageSource.getMessage("insert.transaction.details", null, null);
			  LOGGER.info("Query {}"+ sql); 
			  namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
			  Map<String, Object> namedParameters = new HashMap<String, Object>();
			  namedParameters.put("transactionId", loggingDetails.getTransactionId());
			  namedParameters.put("transactionDesc", loggingDetails.getTransactionDesc());
			  namedParameters.put("transactionRrn", loggingDetails.getTransactionRrn());
			  namedParameters.put("reqInitiatedDt", loggingDetails.getReqInitiatedDt());
			  namedParameters.put("respArrivalDt", loggingDetails.getRespArrivalDt());
			  namedParameters.put("reqProcessingDt", loggingDetails.getReqProcessingDt());
			  namedParameters.put("respDataSize", loggingDetails.getRespDataSize());
			  namedParameters.put("bankId", loggingDetails.getBankId());
			  namedParameters.put("respCode", loggingDetails.getRespCode());
			  namedParameters.put("respDesc", loggingDetails.getRespDesc());
			  namedParameters.put("transactionStatus", loggingDetails.getTransactionStatus());
			  int count = namedParameterJdbcTemplate.update(sql, namedParameters);
			  LOGGER.info("Count :::: "+ count); 
			  if(count>0)
			  { 
				  sql = messageSource.getMessage("insert.transaction.api.metrics.details", null, null);
				  LOGGER.info("Query {}"+ sql);
				  count = namedParameterJdbcTemplate.update(sql, namedParameters);
				  LOGGER.info("Count :::: "+ count); 
				  if(count>0)
					  insertRows=true; 
			  }
			 } 
			catch (Exception e) {
			LOGGER.info("catch block");
			LOGGER.error("The Exception message is::{}" + e);
		  }
		return insertRows;

	}

}
