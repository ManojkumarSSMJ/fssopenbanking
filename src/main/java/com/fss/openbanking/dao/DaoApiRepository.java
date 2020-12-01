/**
 * 
 */
package com.fss.openbanking.dao;

import org.springframework.context.ApplicationContextAware;

import com.fss.openbanking.bean.AccountData;
import com.fss.openbanking.bean.AuthenticationBean;
import com.fss.openbanking.bean.ConsentTokenDetails;
import com.fss.openbanking.bean.CreateResponseData;
import com.fss.openbanking.bean.DirectDebitData;
import com.fss.openbanking.bean.InstitutionData;
import com.fss.openbanking.bean.LoggingDetails;
import com.fss.openbanking.bean.StandingOrderData;
import com.fss.openbanking.bean.TransactionData;

/**
 * @author selvakumara
 *
 */
public interface DaoApiRepository  extends ApplicationContextAware {
	
	boolean saveAccountDetails(ConsentTokenDetails consentTokenDetails, AccountData accountData);

	boolean saveAccountNameDetails(ConsentTokenDetails consentTokenDetails, AccountData accountData);

	boolean saveAccountIdentificationsDetails(ConsentTokenDetails consentTokenDetails, AccountData accountData);
	
	boolean saveAccountBalanceDetails(ConsentTokenDetails consentTokenDetails, AccountData accountData);

	boolean saveTransactionDetails(TransactionData transactionData, AccountData accountData, ConsentTokenDetails consentTokenDetails);

	boolean saveDirectDebitDetails(DirectDebitData directDebitData, AccountData accountData,ConsentTokenDetails consentTokenDetails);

	boolean saveStandingOrdersDetails(StandingOrderData standingOrderData, AccountData accountData, ConsentTokenDetails consentTokenDetails);

	boolean saveStandingOrdersIdentificationDetails(StandingOrderData standingOrderData, AccountData accountData,ConsentTokenDetails consentTokenDetails);

	boolean saveLastUpdatedDateTime(ConsentTokenDetails consentTokenDetails);

	boolean saveInstitutionDetails(InstitutionData institutionData);

	boolean saveCreateUserDetails(AuthenticationBean authenticationBean, CreateResponseData createResponseData);

	String fetchTransactionId();

	boolean saveFetchingDetails(LoggingDetails loggingDetails);

}
