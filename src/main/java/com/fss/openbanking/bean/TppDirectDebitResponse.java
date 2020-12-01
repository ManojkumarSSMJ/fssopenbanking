package com.fss.openbanking.bean;

import java.util.List;

public class TppDirectDebitResponse {

	private String responseFlag;

	private String responseMessage;

	private List<DirectDebitDetails> directDebitDetails;

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

	public List<DirectDebitDetails> getDirectDebitDetails() {
		return directDebitDetails;
	}

	public void setDirectDebitDetails(
			List<DirectDebitDetails> directDebitDetails) {
		this.directDebitDetails = directDebitDetails;
	}

}
