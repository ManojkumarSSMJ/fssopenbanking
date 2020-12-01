/**
 * 
 */
package com.fss.openbanking.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.openbanking.bean.Dashboard;
import com.fss.openbanking.bean.InstitutionDetails;
import com.fss.openbanking.bean.ShowConsent;
import com.fss.openbanking.bean.UserDetails;
import com.fss.openbanking.dao.DaoRepository;

/**
 * @author selvakumara
 *
 */
@Service("AdminService")
public class AdminServiceImpl implements AdminService{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private DaoRepository daoRepository;

	@Override
	public List<ShowConsent> fetchAllConsentDetails() {
		List<ShowConsent> showConsentDetails = null;
		try {
			 showConsentDetails = daoRepository.fetchAllConsentDetails();
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return showConsentDetails;
	}

	@Override
	public List<Dashboard> fetchApiStatisticsList() {
		List<Dashboard> apiStatisticsList = new ArrayList<Dashboard>();
		try {
			Dashboard apiStatistics = null;
			
			//success
			apiStatistics = new Dashboard();
			apiStatistics.setLabel("Success");
			apiStatistics.setColor("#008000");
			apiStatistics.setValue(daoRepository.fetchTransactionStatusDetails("C"));
			apiStatisticsList.add(apiStatistics);
			
			//failure
			apiStatistics = new Dashboard();
			apiStatistics.setLabel("Failure ");
			apiStatistics.setColor("#FF0000");
			apiStatistics.setValue(daoRepository.fetchTransactionStatusDetails("F"));
			apiStatisticsList.add(apiStatistics);
			
			//timeout
			apiStatistics = new Dashboard();
			apiStatistics.setLabel("Time Out");
			apiStatistics.setColor("#f8bd19");
			apiStatistics.setValue(daoRepository.fetchTransactionStatusDetails("T"));
			apiStatisticsList.add(apiStatistics);
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return apiStatisticsList;
	}

	@Override
	public List<Dashboard> fetchMonthWiseApiStatisticsList() {
		List<Dashboard> monthWiseApiStatisticsList = new ArrayList<Dashboard>();
		try {
			Dashboard apiStatistics = null;
			List<Integer> valueList = null;
			int year = Calendar.getInstance().get(Calendar.YEAR);
			
			 String months[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
			
			//success
			apiStatistics = new Dashboard();
			apiStatistics.setLabel("Success");
			apiStatistics.setColor("#008000");
			
			valueList = new ArrayList<Integer>();
			for(String month : months)
			{
				valueList.add(daoRepository.fetchTransactionStatusDetailsbyMoths(month, year, "C"));
			}
			
			apiStatistics.setValueList(valueList);
			monthWiseApiStatisticsList.add(apiStatistics);
			
			//failure
			apiStatistics = new Dashboard();
			apiStatistics.setLabel("Failure ");
			apiStatistics.setColor("#FF0000");
			
			valueList = new ArrayList<Integer>();
			for(String month : months)
			{
				valueList.add(daoRepository.fetchTransactionStatusDetailsbyMoths(month, year, "F"));
			}
			
			apiStatistics.setValueList(valueList);
			monthWiseApiStatisticsList.add(apiStatistics);
			
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return monthWiseApiStatisticsList;
	}

	@Override
	public List<Dashboard> fetchBankWiseUserStatisticsList() {
		List<Dashboard> monthWiseApiStatisticsList = new ArrayList<Dashboard>();
		try {
			Dashboard monthWiseApiStatistics = null;
			int count = 0;
			
			List<InstitutionDetails> institutionDetailsList = daoRepository.fetchTppInstDetails();
			
			for(InstitutionDetails institutionDetails : institutionDetailsList)
			{
				count = 0;
				monthWiseApiStatistics = new Dashboard();
				monthWiseApiStatistics.setBankName(institutionDetails.getBankName());
				
				List<UserDetails> userDetailsList = daoRepository.fetchUserDetails();
				for(UserDetails userDetails : userDetailsList)
				{
					if(daoRepository.fetchBankConsentDetailsbyUser(institutionDetails,userDetails) > 0)
						count ++;
				}
				monthWiseApiStatistics.setCount(count);
				monthWiseApiStatisticsList.add(monthWiseApiStatistics);
			}
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return monthWiseApiStatisticsList;
	}

	@Override
	public List<Dashboard> fetchBankWiseConsentStatisticsList() {
		List<Dashboard> monthWiseApiStatisticsList = new ArrayList<Dashboard>();
		try {
			Dashboard monthWiseApiStatistics = null;
			int count = 0;
			
			List<InstitutionDetails> institutionDetailsList = daoRepository.fetchTppInstDetails();
			
			for(InstitutionDetails institutionDetails : institutionDetailsList)
			{
				count = 0;
				monthWiseApiStatistics = new Dashboard();
				monthWiseApiStatistics.setBankName(institutionDetails.getBankName());
				
				count = daoRepository.fetchBankConsentDetailsbyBank(institutionDetails);
				monthWiseApiStatistics.setCount(count);
				
				monthWiseApiStatisticsList.add(monthWiseApiStatistics);
			}
			
		} catch(Exception e) {
			LOGGER.error("catch block");
			LOGGER.error("Failed!", e);
		}
		return monthWiseApiStatisticsList;
	}
	


}
