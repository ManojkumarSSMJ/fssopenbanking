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
import com.fss.openbanking.bean.TppAccountsResponse;
import com.fss.openbanking.bean.TppBalanceResponse;
import com.fss.openbanking.bean.TppTransactionResponse;
import com.fss.openbanking.bean.UserDetails;
import com.fss.openbanking.service.AccountAccessService;

/**
 * @author selvakumara
 *
 */
@Controller
public class AccountAccessController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AccountAccessController.class);
	
	@Autowired
	private AccountAccessService accountAccessService;

	@RequestMapping("tppAccounts")
	public ModelAndView tppAccounts(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page.tpp.accounts");
		try {
			LOGGER.info("Tpp Accounts");
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			TppAccountsResponse tppAccountsResponse = accountAccessService.getTppAccountsResponse(userDetails);
			String responseFlag = request.getParameter("responseFlag");
			String responseMessage = request.getParameter("responseMessage");
			if(responseFlag !=null && !"".equals(responseFlag)) {
				mav.addObject("responseFlag", responseFlag);
				mav.addObject("responseMessage", responseMessage);
				mav.addObject("consent", "true");
			} else {
				mav.addObject("responseFlag", tppAccountsResponse.getResponseFlag());
				mav.addObject("responseMessage", tppAccountsResponse.getResponseMessage());
			}
		
			mav.addObject("tppAccountsResponse", tppAccountsResponse);
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	

	@RequestMapping("tppBalance")
	public ModelAndView tppBalance(HttpServletRequest request, TppAccountsRequest tppAccountsRequest) {
		ModelAndView mav = new ModelAndView("page.tpp.balance");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			TppBalanceResponse hubBalanceResponse = accountAccessService.gettppBalanceResponse(userDetails.getUserId(), tppAccountsRequest);
			mav.addObject("tppBalanceResponse", hubBalanceResponse);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping("tppTransactions")
	public ModelAndView tppTransactions(HttpServletRequest request, TppAccountsRequest tppAccountsRequest) {
		ModelAndView mav = new ModelAndView("page.tpp.transaction");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			TppTransactionResponse tppTransactionResponse = accountAccessService.gettppTransactionResponse(userDetails, tppAccountsRequest);
			mav.addObject("tppTransactionResponse",tppTransactionResponse);
			mav.addObject("bankName", tppAccountsRequest.getBankName());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping("tppAllTransactions")
	public ModelAndView tppAllTransactions(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page.tpp.transaction");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			TppTransactionResponse tppTransactionResponse = accountAccessService.gettppAllTransactionResponse(userDetails);
			mav.addObject("tppTransactionResponse",tppTransactionResponse);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping("tppDirectDebits")
	public ModelAndView tppDirectDebits(HttpServletRequest request, TppAccountsRequest tppAccountsRequest) {
		ModelAndView mav = new ModelAndView("page.tpp.directdebits");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			mav.addObject("tppDirectDebitResponse", accountAccessService.getTppDirectDebitResponse(userDetails, tppAccountsRequest));
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
	@RequestMapping("tppAllDirectDebits")
	public ModelAndView tppAllDirectDebits(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page.tpp.directdebits");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			mav.addObject("tppDirectDebitResponse", accountAccessService.getTppAllDirectDebitResponse(userDetails));
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
	@RequestMapping("tppStandingOrders")
	public ModelAndView tppStandingOrders(HttpServletRequest request, TppAccountsRequest tppAccountsRequest) {
		ModelAndView mav = new ModelAndView("page.tpp.standingorders");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			mav.addObject("tppStandingOrderResponse", accountAccessService.getTppStandingOrderResponse(userDetails,tppAccountsRequest));
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
	@RequestMapping("tppAllStandingOrders")
	public ModelAndView tppAllStandingOrders(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page.tpp.standingorders");
		try {
			HttpSession session = request.getSession();
			UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
			mav.addObject("tppStandingOrderResponse", accountAccessService.getTppAllStandingOrderResponse(userDetails));
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
}
