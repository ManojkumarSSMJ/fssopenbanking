/**
 * 
 */
package com.fss.openbanking.service;

import com.fss.openbanking.bean.TppAccountsRequest;
import com.fss.openbanking.bean.TppAccountsResponse;
import com.fss.openbanking.bean.UserDetails;
import com.fss.openbanking.bean.UserResponse;

/**
 * @author selvakumara
 *
 */
public interface ApiService {

	UserResponse getInstitutions();

	TppAccountsResponse updateBankDetailsByBank(UserDetails userDetails, TppAccountsRequest tppAccountsRequest);

	TppAccountsResponse tppUpdateBankDetails(UserDetails userDetails);

}
