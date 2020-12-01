/**
 * 
 */
package com.fss.openbanking.bean;

import java.util.List;

/**
 * @author selvakumara
 *
 */
public class TppConsentResponse {

	private String responseFlag;

	private String responseMessage;
	
	private String authenticateUrl;
	
	private String transactionId;
	
	private String accountData;
	
	private List<UpdateDTDetails> updateDTDetailsList;
	
	private List<InstitutionDetails> institutionDetailsList;
	
	public String getResponseFlag() {
		return responseFlag;
	}

	public void setResponseFlag(String responseFlag) {
		this.responseFlag = responseFlag;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getAuthenticateUrl() {
		return authenticateUrl;
	}

	public void setAuthenticateUrl(String authenticateUrl) {
		this.authenticateUrl = authenticateUrl;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public List<UpdateDTDetails> getUpdateDTDetailsList() {
		return updateDTDetailsList;
	}

	public void setUpdateDTDetailsList(List<UpdateDTDetails> updateDTDetailsList) {
		this.updateDTDetailsList = updateDTDetailsList;
	}

	public List<InstitutionDetails> getInstitutionDetailsList() {
		return institutionDetailsList;
	}

	public void setInstitutionDetailsList(List<InstitutionDetails> institutionDetailsList) {
		this.institutionDetailsList = institutionDetailsList;
	}

	public String getAccountData() {
		return accountData;
	}

	public void setAccountData(String accountData) {
		this.accountData = accountData;
	}

}
