/**
 * 
 */
package com.fss.openbanking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fss.openbanking.bean.TppConsentForm;
import com.fss.openbanking.bean.TppConsentResponse;
import com.fss.openbanking.bean.UserDetails;
import com.fss.openbanking.service.SettingsService;

/**
 * @author Selvakumar
 *
 */
@Controller
public class SettingsController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(SettingsController.class);
	
	@Autowired
	private SettingsService settingsService;
	
	@RequestMapping("showBanks")
	public ModelAndView showBank(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page.showbank");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			mav.addObject("instDetails", settingsService.fetchInstDetails(userDetails.getUserId()));
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
	@RequestMapping("fetchConsent")
	public ModelAndView fetchConsent(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page.show.consent");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			mav.addObject("consentTokenDetails", settingsService.fetchExistingConsentDetails(userDetails));
			mav.addObject("responseFlag", request.getParameter("responseFlag"));
			mav.addObject("responseMessage", request.getParameter("responseMessage"));
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
	@RequestMapping("consentUpdate")
	public ModelAndView consentUpdate(HttpServletRequest request, TppConsentForm tppConsentForm) {
		ModelAndView mav = new ModelAndView("page.show.consent");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			TppConsentResponse tppConsentResponse = settingsService.processTppConsentResponse(userDetails, tppConsentForm);
			if("1".equalsIgnoreCase(tppConsentResponse.getResponseFlag())) {

				userDetails.setAccountData(tppConsentResponse.getAccountData());
				userDetails.setConsentTokenFlag("1");
				request.getSession().setAttribute("userDetails", userDetails);
				
				return  new ModelAndView("redirect:" + tppConsentResponse.getAuthenticateUrl());
			}
			mav.addObject("responseFlag", tppConsentResponse.getResponseFlag());
			mav.addObject("responseMessage", tppConsentResponse.getResponseMessage());
			mav.addObject("url", "fetchConsent");

		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
	@RequestMapping("revokeConsent")
	public ModelAndView revokeConsent(HttpServletRequest request, TppConsentForm tppConsentForm) {
		ModelAndView mav = new ModelAndView("page.show.consent");

		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			TppConsentResponse tppConsentResponse = settingsService.revokeConsent(userDetails.getUserId(), tppConsentForm);
			if("1".equalsIgnoreCase(tppConsentResponse.getResponseFlag())) {
			     mav = new ModelAndView("page.mpayredirect");
			}
			mav.addObject("responseFlag", tppConsentResponse.getResponseFlag());
			mav.addObject("responseMessage", tppConsentResponse.getResponseMessage());
			mav.addObject("url", "fetchConsent");
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
	
	@RequestMapping("redirectPage")
	public ModelAndView redirectPage(HttpServletRequest request, TppConsentForm tppConsentForm) {
		ModelAndView mav = null;
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			TppConsentResponse tppConsentResponse = null;
			String token = request.getParameter("consent");
			
			if("2".equals(userDetails.getConsentTokenFlag()))
			{
				mav = new ModelAndView("page.show.consent");
				
				if(token != null)
				    tppConsentResponse = settingsService.processPaymentTokenConsent(userDetails, tppConsentForm, token);
				
				JSONObject rechargeDetails = new JSONObject(userDetails.getPaymentData().split("=")[1].toString());
				
				userDetails.setPaymentData(null);
				userDetails.setConsentTokenFlag(null);
				request.getSession().setAttribute("userDetails", userDetails);
				
				mav = new ModelAndView("page.mpayredirect");
				
				mav.addObject("url", "tppPayments");
			    mav.addObject("responseFlag", tppConsentResponse.getResponseFlag() !=null ? tppConsentResponse.getResponseFlag() : "0");
			    mav.addObject("userId", userDetails.getUserId());
				mav.addObject("accountNumber",rechargeDetails.get("accountNumber"));
				mav.addObject("bankName", rechargeDetails.get("bankName"));
				mav.addObject("provider", rechargeDetails.get("provider"));
				mav.addObject("mobileNumber", rechargeDetails.get("mobileNumber"));
				mav.addObject("amount", rechargeDetails.get("amount"));
				mav.addObject("transactionId", tppConsentResponse.getTransactionId());
			}
			if("1".equals(userDetails.getConsentTokenFlag()))
			{
				mav = new ModelAndView("page.show.consent");
			    
				if(token != null)
				    tppConsentResponse = settingsService.processAccountTokenConsent(userDetails, tppConsentForm, token);
				
				userDetails.setAccountData(null);
				userDetails.setConsentTokenFlag(null);
				request.getSession().setAttribute("userDetails", userDetails);
				
				mav = new ModelAndView("page.mpayredirect");
				mav.addObject("responseFlag", tppConsentResponse.getResponseFlag() !=null ? tppConsentResponse.getResponseFlag() : "0");
				mav.addObject("responseMessage", tppConsentResponse.getResponseMessage());
				mav.addObject("url", "fetchConsent");
			}
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
	@RequestMapping("fetchActivities")
	public ModelAndView fetchActivities(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page.show.activity");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			mav.addObject("tppConsentResponse", settingsService.fetchfetchActivitiesDetails(userDetails));
			mav.addObject("responseFlag", request.getParameter("responseFlag"));
			mav.addObject("responseMessage", request.getParameter("responseMessage"));
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
}
