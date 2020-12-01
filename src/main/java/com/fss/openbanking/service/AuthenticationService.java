/**
 * 
 */
package com.fss.openbanking.service;

import javax.servlet.http.HttpServletRequest;

import com.fss.openbanking.bean.AuthenticationBean;
import com.fss.openbanking.bean.ResponseDetails;

/**
 * @author selvakumara
 *
 */
public interface AuthenticationService {

	ResponseDetails authenticate(AuthenticationBean authenticationBean, HttpServletRequest request);

	ResponseDetails tppCreateAccount(AuthenticationBean authenticationBean);
}
