/**
 * 
 */
package com.fss.openbanking.dao;

import java.util.List;

import org.springframework.context.ApplicationContextAware;

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
public interface DaoRepository  extends ApplicationContextAware {
	
	List<UserDetails> fetchUserDetails();

	List<InstitutionDetails> fetchTppInstDetails();

	List<ConsentDetails> fetchInstFeatureDetailsByBankId(String bankId);

	InstitutionDetails fetchInstDetailsByBankId(String bankId);

	List<ConsentTokenDetails> fetchConsentDetailsByUser(String mobileNumber);

	List<ConsentDetails> fetchConsentDetailsByBank(ConsentTokenDetails consentDetail);

	String getUuIdusingMobileNumber(String mobileNumber);

	boolean saveConsentTemp(ConsentResponse consentResponse);

	ConsentTokenDetails fetchConsentBankId(String bankId,String mobileNumber);

	List<TppBalanceResponse> fetchBalanceDetailsByBankId(TppAccountsRequest tppAccountsRequest, String userId);

	List<AccountData> fetchAccountDetailsByUserId(String userId);

	List<AccountIdentifications> fetchAccountIdentificationsDetails(AccountData accountDetail);

	boolean saveTokenDetails(ConsentResponse consentResponse, String token);

	List<TransactionData> fetchTransactionDetails(UserDetails userDetails, TppAccountsRequest tppAccountsRequest);

	List<DirectDebitDetails> fetchDirectDebitsDetails(UserDetails userDetails, TppAccountsRequest tppAccountsRequest);

	List<StandingOrderDetails> fetchStandingOrdersDetails(UserDetails userDetails, TppAccountsRequest tppAccountsRequest);

	List<AccountNames> fetchAccountNamesDetails(AccountData accountDetail);

	List<AccountIdentifications> fetchStandingOrdersIdentificationsDetails(StandingOrderDetails standingOrderDetail);

	String fetchLastUpdateDateTime(String mobileNumber, String bankId, String bankName);

	boolean deleteConsentTemp(TppConsentForm tppConsentForm);

	List<TransactionData> fetchAllTransactionDetails(UserDetails userDetails);

	List<DirectDebitDetails> fetchAllDirectDebitsDetails(UserDetails userDetails);

	List<StandingOrderDetails> fetchAllStandingOrdersDetails(UserDetails userDetails);

	String fetchUpdateHoursfromInstitutions(ConsentTokenDetails consentTokenDetails);

	List<AccountData> fetchAccountDetailsByBank(String bankId, String mobileNumber);

	int tppCreateAccount(AuthenticationBean authenticationBean);

	List<UpdateDTDetails> fetchUpdatedDetails(UserDetails userDetails);

	List<ShowConsent> fetchAllConsentDetails();

	int fetchTransactionStatusDetails(String transactinStatus);

	int fetchTransactionStatusDetailsbyMoths(String month, int year, String transactinStatus);

	int fetchBankConsentDetailsbyUser(InstitutionDetails institutionDetails, UserDetails userDetails);

	int fetchBankConsentDetailsbyBank(InstitutionDetails institutionDetails);

}
