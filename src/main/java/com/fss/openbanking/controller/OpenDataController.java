/**
 * 
 */
package com.fss.openbanking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fss.openbanking.bean.TppAccountsRequest;
import com.fss.openbanking.bean.UserDetails;
import com.fss.openbanking.service.AccountAccessService;

/**
 * @author selvakumara
 *
 */
@Controller
public class OpenDataController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(OpenDataController.class);
	
	@Autowired
	private AccountAccessService accountAccessService;

	@RequestMapping("tppAtm")
	public ModelAndView tppAtm(HttpServletRequest request, TppAccountsRequest tppAccountsRequest) {
		ModelAndView mav = new ModelAndView("page.tpp.atm");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			mav.addObject("tppProductsResponse", accountAccessService.gettppProductsResponse(userDetails.getUserId(), tppAccountsRequest));
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
	@RequestMapping("tppBranch")
	public ModelAndView tppBranch(HttpServletRequest request, TppAccountsRequest tppAccountsRequest) {
		ModelAndView mav = new ModelAndView("page.tpp.branch");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			mav.addObject("tppProductsResponse", accountAccessService.gettppProductsResponse(userDetails.getUserId(), tppAccountsRequest));
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
}
