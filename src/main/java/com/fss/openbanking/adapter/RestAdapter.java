/**
 * 
 */
package com.fss.openbanking.adapter;

import org.json.JSONObject;

import com.fss.openbanking.bean.AccountAdapter;
import com.fss.openbanking.bean.AdapterResponse;

/**
 * @author selvakumara
 *
 */
public interface RestAdapter {

	AdapterResponse callAccountsDetailsApi(AccountAdapter accountAdapter);
	
	AdapterResponse callFundTransferConsent(AccountAdapter accountAdapter);
	
	AdapterResponse callConsentInitiation(AccountAdapter accountAdapter);
	
	AdapterResponse callFetchConsent(AccountAdapter accountAdapter);
	
	AdapterResponse callDeleteConsent(AccountAdapter accountAdapter);

	AdapterResponse callPayAuthorization(AccountAdapter accountAdapter, JSONObject payAuthReq);

	AdapterResponse callPayment(AccountAdapter accountAdapter);

	AdapterResponse callInstitutions(AccountAdapter accountAdapter);

	AdapterResponse callCreateUser(AccountAdapter accountAdapter);
	
}
