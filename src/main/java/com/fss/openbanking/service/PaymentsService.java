package com.fss.openbanking.service;

import com.fss.openbanking.bean.AuthenticationBean;
import com.fss.openbanking.bean.TppPaymentsResponse;
import com.fss.openbanking.bean.UserDetails;

public interface PaymentsService {

	TppPaymentsResponse fetchPaymentAccounts(UserDetails userDetails);

	TppPaymentsResponse createPaymentAuthorization(AuthenticationBean authenticationBean, UserDetails userDetails);

}
