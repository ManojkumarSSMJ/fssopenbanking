package com.fss.openbanking.bean;

public class DirectDebitResponse {

	private String id;
	
	private StatusDetails statusDetails;
	
	private PayeeDetails payeeDetails;
	
	private String reference;
	
	private TransactionAmount previousPaymentAmountDetails;
	
	private String previousPaymentDateTime;
	
	private String accountId;
	
	private String maskAccountId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StatusDetails getStatusDetails() {
		return statusDetails;
	}

	public void setStatusDetails(StatusDetails statusDetails) {
		this.statusDetails = statusDetails;
	}

	public PayeeDetails getPayeeDetails() {
		return payeeDetails;
	}

	public void setPayeeDetails(PayeeDetails payeeDetails) {
		this.payeeDetails = payeeDetails;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public TransactionAmount getPreviousPaymentAmountDetails() {
		return previousPaymentAmountDetails;
	}

	public void setPreviousPaymentAmountDetails(TransactionAmount previousPaymentAmountDetails) {
		this.previousPaymentAmountDetails = previousPaymentAmountDetails;
	}

	public String getPreviousPaymentDateTime() {
		return previousPaymentDateTime;
	}

	public void setPreviousPaymentDateTime(String previousPaymentDateTime) {
		this.previousPaymentDateTime = previousPaymentDateTime;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getMaskAccountId() {
		return maskAccountId;
	}

	public void setMaskAccountId(String maskAccountId) {
		this.maskAccountId = maskAccountId;
	}

	
	
}
