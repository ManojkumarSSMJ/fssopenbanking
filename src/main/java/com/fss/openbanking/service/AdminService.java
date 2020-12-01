/**
 * 
 */
package com.fss.openbanking.service;

import java.util.List;

import com.fss.openbanking.bean.Dashboard;
import com.fss.openbanking.bean.ShowConsent;

/**
 * @author selvakumara
 *
 */
public interface AdminService {

	List<ShowConsent> fetchAllConsentDetails();

	List<Dashboard> fetchApiStatisticsList();

	List<Dashboard> fetchMonthWiseApiStatisticsList();

	List<Dashboard> fetchBankWiseUserStatisticsList();

	List<Dashboard> fetchBankWiseConsentStatisticsList();

}
