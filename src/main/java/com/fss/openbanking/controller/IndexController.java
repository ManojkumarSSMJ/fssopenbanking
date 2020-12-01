/**
 * 
 */
package com.fss.openbanking.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Selvakumar
 *
 */
@Controller
public class IndexController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping("")
	public ModelAndView loadIndex() {
		LOGGER.debug("Open Index Page");
		return new ModelAndView("page.index");
	}
	
	@RequestMapping("index")
	public ModelAndView getIndex() {
		LOGGER.debug("Open Index Page");
		return new ModelAndView("page.index");
		
	}
	
	@RequestMapping("tppLogin")
	public ModelAndView openLogin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page.login");
		try {
			LOGGER.debug("Open Login Page");
			mav.addObject("responseFlag", request.getParameter("responseFlag"));
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	
		
	}
	
	@RequestMapping("tppSingUP")
	public ModelAndView openSignUp(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page.signUp");
		try {
		LOGGER.debug("Open Create Account Page");
		mav.addObject("responseFlag", request.getParameter("responseFlag"));
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return mav;
	}
	
}
