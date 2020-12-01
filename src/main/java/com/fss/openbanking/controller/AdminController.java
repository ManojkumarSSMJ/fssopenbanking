/**
 * 
 */
package com.fss.openbanking.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fss.openbanking.bean.Dashboard;
import com.fss.openbanking.bean.ShowConsent;
import com.fss.openbanking.service.AdminService;

/**
 * @author Selvakumar
 *
 */
@Controller
public class AdminController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("showConsent")
	public ModelAndView showConsent(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page.showConsent");
		try {
			List<ShowConsent> showConsentDetails = adminService.fetchAllConsentDetails();
			mav.addObject("showConsentDetails",showConsentDetails);
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
	@RequestMapping("dashboard")
	public ModelAndView dassboard(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page.dashboard");
		try {
			 String months[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

			List<Dashboard> apiStatisticsList = adminService.fetchApiStatisticsList();
			
			List<Dashboard> monthWiseApiStatisticsList = adminService.fetchMonthWiseApiStatisticsList();
			
			List<Dashboard> bankWiseUserStatisticsList = adminService.fetchBankWiseUserStatisticsList();
			
			List<Dashboard> bankWiseConsentStatisticsList = adminService.fetchBankWiseConsentStatisticsList();
			
			mav.addObject("apiStatisticsList",apiStatisticsList);
			mav.addObject("monthWiseApiStatisticsList",monthWiseApiStatisticsList);
			mav.addObject("bankWiseUserStatisticsList",bankWiseUserStatisticsList);
			mav.addObject("bankWiseConsentStatisticsList",bankWiseConsentStatisticsList);
			mav.addObject("months",months);
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
}
