package com.fss.openbanking.bean;

public class DirectDebitDetails {

	private String directDebitId;
	
	private String accountId;
	
	private String maskAccountId;
	
	private String payeeName;
	
	private String prevPaymentDate;
	
	private String prevPaymentAmount;
	
	private String debitStatusCode;

	public String getDirectDebitId() {
		return directDebitId;
	}

	public void setDirectDebitId(String directDebitId) {
		this.directDebitId = directDebitId;
	}

	public String getPrevPaymentDate() {
		return prevPaymentDate;
	}

	public void setPrevPaymentDate(String prevPaymentDate) {
		this.prevPaymentDate = prevPaymentDate;
	}

	public String getPrevPaymentAmount() {
		return prevPaymentAmount;
	}

	public void setPrevPaymentAmount(String prevPaymentAmount) {
		this.prevPaymentAmount = prevPaymentAmount;
	}

	public String getDebitStatusCode() {
		return debitStatusCode;
	}

	public void setDebitStatusCode(String debitStatusCode) {
		this.debitStatusCode = debitStatusCode;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
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
