package com.fss.openbanking.bean;

public class ConsentDetails {

	private String consentId;
	
	private String consentShortName;
	
	private String consentDisplayName;
	
	private String consentStatus;
	
	private String expiryDate;
	
	private String transactionStartDate;
	
	private String transactionEndDate;
	
	private String state;
	
	private String nonce;
	
	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
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

	public String getConsentStatus() {
		return consentStatus;
	}

	public void setConsentStatus(String consentStatus) {
		this.consentStatus = consentStatus;
	}

	public String getConsentId() {
		return consentId;
	}

	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}

	public String getConsentDisplayName() {
		return consentDisplayName;
	}

	public void setConsentDisplayName(String consentDisplayName) {
		this.consentDisplayName = consentDisplayName;
	}

	public String getConsentShortName() {
		return consentShortName;
	}

	public void setConsentShortName(String consentShortName) {
		this.consentShortName = consentShortName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

}
