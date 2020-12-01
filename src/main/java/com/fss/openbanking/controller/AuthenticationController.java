/**
 * 
 */
package com.fss.openbanking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fss.openbanking.bean.AuthenticationBean;
import com.fss.openbanking.bean.ResponseDetails;
import com.fss.openbanking.bean.UserDetails;
import com.fss.openbanking.service.AuthenticationService;

/**
 * @author selvakumara
 *
 */
@Controller
public class AuthenticationController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authenticationService;
	
	@RequestMapping("tppAuthenticate")
	public ModelAndView tppAuthenticate(AuthenticationBean authenticationBean, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			ResponseDetails responseDetails = authenticationService.authenticate(authenticationBean, request);
			if("1".equals(responseDetails.getResponseFlag())) {
				LOGGER.info("Authentication");
				if("2".equals(responseDetails.getRole()))
					mav = new ModelAndView("page.tppHome");
				else
					mav = new ModelAndView("page.tppAdminHome");
						
						
			} else {
				LOGGER.info("Authentication");
				 mav = new ModelAndView("page.mpayredirect");
				 mav.addObject("responseFlag", "0");
				 mav.addObject("url", "tppLogin");
			}
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
	@RequestMapping("tppCreateAccount")
	public ModelAndView tppCreateAccount(AuthenticationBean authenticationBean, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			
			ResponseDetails responseDetails = authenticationService.tppCreateAccount(authenticationBean);
		    mav = new ModelAndView("page.mpayredirect");
			mav.addObject("responseFlag", responseDetails.getResponseFlag());
			mav.addObject("responseMessage", responseDetails.getResponseMessage());
			mav.addObject("url", "tppSingUP");
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
	  @RequestMapping("logout")
	    public ModelAndView getLogoutPage(HttpServletRequest request, HttpServletResponse response){

			LOGGER.info("logout");
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
	        if(userDetails != null) {
	        	session.setAttribute("userDetails", null);	        	
	        } 
	        if(session != null)
	        	session.invalidate();
	       
	        return new ModelAndView(new RedirectView("/index", true));
	    }
}
