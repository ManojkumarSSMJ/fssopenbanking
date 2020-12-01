package com.fss.openbanking.bean;

import java.util.List;

public class LoginResponse {

	private List<String> accountList;
	private String responseFlag;
	private String responseMessage;
	private UserDetails userDetails;
	private String transactionStartDate;
	private String transactionEndDate;
	private String accessExpiryDate;
	
	public List<String> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<String> accountList) {
		this.accountList = accountList;
	}

	

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

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

	public String getTransactionStartDate() {
		return transactionStartDate;
	}

	public void setTransactionStartDate(String transactionStartDate) {
		this.transactionStartDate = transactionStartDate;
	}

	public String getTransactionEndDate() {
		return transactionEndDate;
	}

	public void setTransactionEndDate(String transactionEndDate) {
		this.transactionEndDate = transactionEndDate;
	}

	public String getAccessExpiryDate() {
		return accessExpiryDate;
	}

	public void setAccessExpiryDate(String accessExpiryDate) {
		this.accessExpiryDate = accessExpiryDate;
	}

	
	
}
