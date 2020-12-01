/**
 * 
 */
package com.fss.openbanking.service;

import com.fss.openbanking.bean.TppAccountsRequest;
import com.fss.openbanking.bean.TppAccountsResponse;
import com.fss.openbanking.bean.TppBalanceResponse;
import com.fss.openbanking.bean.TppDirectDebitResponse;
import com.fss.openbanking.bean.TppProductsResponse;
import com.fss.openbanking.bean.TppStandingOrderResponse;
import com.fss.openbanking.bean.TppTransactionResponse;
import com.fss.openbanking.bean.UserDetails;

/**
 * @author selvakumara
 *
 */
public interface AccountAccessService {

	TppAccountsResponse getTppAccountsResponse(UserDetails userDetails);
	
	TppBalanceResponse gettppBalanceResponse(String userId, TppAccountsRequest hubAccountsRequest);
	
	TppTransactionResponse gettppTransactionResponse(UserDetails userDetails, TppAccountsRequest hubAccountsRequest);

	TppProductsResponse gettppProductsResponse(String userId, TppAccountsRequest tppAccountsRequest);

	TppDirectDebitResponse getTppDirectDebitResponse(UserDetails userDetails, TppAccountsRequest tppAccountsRequest);

	TppStandingOrderResponse getTppStandingOrderResponse(UserDetails userDetails, TppAccountsRequest tppAccountsRequest);

	TppTransactionResponse gettppAllTransactionResponse(UserDetails userDetails);

	TppDirectDebitResponse getTppAllDirectDebitResponse(UserDetails userDetails);

	TppStandingOrderResponse getTppAllStandingOrderResponse(UserDetails userDetails);

}
