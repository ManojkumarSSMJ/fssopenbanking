/**
 * 
 */
package com.fss.openbanking.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fss.openbanking.adapter.RestAdapter;
import com.fss.openbanking.bean.AccountAdapter;
import com.fss.openbanking.bean.AdapterResponse;
import com.fss.openbanking.bean.AuthenticationBean;
import com.fss.openbanking.bean.ResponseDetails;
import com.fss.openbanking.bean.UserDetails;
import com.fss.openbanking.constants.TppConstants;
import com.fss.openbanking.dao.DaoRepository;
import com.fss.openbanking.responseformatter.AccountsResponseFormatter;

/**
 * @author selvakumara
 *
 */
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	private DaoRepository daoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RestAdapter restAdapter;
	
	@Autowired
	private AccountsResponseFormatter accountsResponseFormatter; 
	
	@Override
	public ResponseDetails authenticate(AuthenticationBean authenticationBean, HttpServletRequest request) {
		ResponseDetails responseDetails = new ResponseDetails();
		responseDetails.setResponseFlag("0");
		responseDetails.setResponseMessage("User name or password is incorrect");
		try {
			authenticationBean.setEncodedPassword(passwordEncoder.encode((CharSequence)authenticationBean.getPassword()));
			List<UserDetails> userDetailsList = daoRepository.fetchUserDetails();
			if(!userDetailsList.isEmpty()) {
				UserDetails userDetails = null;
				for(UserDetails user : userDetailsList)
				{
					if(user.getUserId().equals(authenticationBean.getUserId()) && user.getRole().equals(authenticationBean.getRole()))
					{
						if(passwordEncoder.matches(authenticationBean.getPassword(), user.getPassword()))
						{
							userDetails = user;
							break;
						}
					}
				}
				if(userDetails !=null )
				{
					responseDetails.setResponseFlag("1");
					responseDetails.setRole(userDetails.getRole());
					HttpSession session = request.getSession();
					if (session!=null && !session.isNew()) {
					    session.invalidate();
					}
					HttpSession newsession = request.getSession(true); // create the session
					newsession.setAttribute("userDetails",userDetails);
				}
			}
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return responseDetails;
	}

	@Override
	public ResponseDetails tppCreateAccount(AuthenticationBean authenticationBean) {
		ResponseDetails responseDetails = new ResponseDetails();
		responseDetails.setResponseFlag("0");
    	responseDetails.setResponseMessage("User creation failed");
		try {
			    int result = 0;
			    boolean createUserDetails = false;
			    
			    
			    AccountAdapter accountAdapter = new AccountAdapter();
				accountAdapter.setUrl( TppConstants.yapilyUrl + "/" + TppConstants.createuserUrl);
				accountAdapter.setCustomerName(authenticationBean.getCustomerName());
				accountAdapter.setMobileNumber(authenticationBean.getMobileNumber());
				AdapterResponse adapterResponse = restAdapter.callCreateUser(accountAdapter);
				if("201".equalsIgnoreCase(adapterResponse.getStatusCode())) {
					createUserDetails = accountsResponseFormatter.formatCreateUserDetails(adapterResponse.getData(), authenticationBean);
				}
				else if("409".equalsIgnoreCase(adapterResponse.getStatusCode())) {
					responseDetails.setResponseFlag("2");
			    	responseDetails.setResponseMessage("User already linked with Yapily");
			    	return responseDetails;
			   }
				else
					createUserDetails = false;
				
				if(!createUserDetails)
				{
					responseDetails.setResponseFlag("0");
			    	responseDetails.setResponseMessage("User creation failed");
			    	return responseDetails;
				}
				
				authenticationBean.setEncodedPassword(passwordEncoder.encode((CharSequence)authenticationBean.getPassword()));
				
				result = daoRepository.tppCreateAccount(authenticationBean);
				
				if(result == 1)
				{
					responseDetails.setResponseFlag("1");
			    	responseDetails.setResponseMessage("User Created Successfully");
				}
				else if((result == 2))
				{
					responseDetails.setResponseFlag("2");
			    	responseDetails.setResponseMessage("User already linked with FSS OpenBanking HUB");
				}
				
				
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return responseDetails;
	}

}
