package com.fss.openbanking.bean;

import java.util.List;

public class TppAccountsResponse {

	private String responseFlag;

	private String responseMessage;

	private List<AccountsResponse> accountsResponseList;

	private List<InstitutionDetails> institutionDetails;
	
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

	public List<InstitutionDetails> getInstitutionDetails() {
		return institutionDetails;
	}

	public void setInstitutionDetails(List<InstitutionDetails> institutionDetails) {
		this.institutionDetails = institutionDetails;
	}

	public List<AccountsResponse> getAccountsResponseList() {
		return accountsResponseList;
	}

	public void setAccountsResponseList(List<AccountsResponse> accountsResponseList) {
		this.accountsResponseList = accountsResponseList;
	}


	

}
