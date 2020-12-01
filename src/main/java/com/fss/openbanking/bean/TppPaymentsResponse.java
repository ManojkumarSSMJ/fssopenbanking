package com.fss.openbanking.bean;

import java.util.List;

public class TppPaymentsResponse {

	private String responseFlag;

	private String authorizationUrl;
	
	private String paymentData;
	
	private String responseMessage;

	private List<AccountsResponse> accountsResponseList;

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

	public List<AccountsResponse> getAccountsResponseList() {
		return accountsResponseList;
	}

	public void setAccountsResponseList(List<AccountsResponse> accountsResponseList) {
		this.accountsResponseList = accountsResponseList;
	}

	public String getAuthorizationUrl() {
		return authorizationUrl;
	}

	public void setAuthorizationUrl(String authorizationUrl) {
		this.authorizationUrl = authorizationUrl;
	}

	public String getPaymentData() {
		return paymentData;
	}

	public void setPaymentData(String paymentData) {
		this.paymentData = paymentData;
	}

	
	
}
