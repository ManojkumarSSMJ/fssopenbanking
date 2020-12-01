package com.fss.openbanking.responseformatter;

import java.util.List;

import com.fss.openbanking.bean.AccountBalances;
import com.fss.openbanking.bean.AccountData;
import com.fss.openbanking.bean.AuthenticationBean;
import com.fss.openbanking.bean.ConsentResponse;
import com.fss.openbanking.bean.DirectDebitData;
import com.fss.openbanking.bean.InstitutionData;
import com.fss.openbanking.bean.StandingOrderData;
import com.fss.openbanking.bean.TransactionData;

public interface AccountsResponseFormatter {

	List<AccountData> formatAccountsResponse(String data);

	ConsentResponse formatConsentInitiationResponse(String data);

	List<TransactionData> formatTransactionDetails(String data);

	List<DirectDebitData> formatDirectDebitDetails(String data);

	List<StandingOrderData> formatStandingOrderDetails(String data);

	List<InstitutionData> formatInstitutionDetails(String data);

	boolean formatCreateUserDetails(String data, AuthenticationBean authenticationBean);

	List<AccountBalances> formatBalanceDetails(String data);

}
