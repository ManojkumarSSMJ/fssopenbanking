package com.fss.openbanking.bean;

import java.util.List;

public class ConsentTokenDetails {
	
	private String mobileNumber;
	private String bankId;
	private String bankName;
	private String uuId;
	private String consentId;
	private String consentToken;
	private String consentStatus;
	private String dateTime;
	private String state;
	private String nonce;	
	private String selectAll;
	private String startDate;
	private String endDate;
	private String expiryDate;
	private String accountId;
	private List<ConsentDetails> consentDetails;
	private String bankLogo;
	private String bankIcon;
	private List<AccountsResponse> accountDetails;

	
	
	public List<ConsentDetails> getConsentDetails() {
		return consentDetails;
	}
	public void setConsentDetails(List<ConsentDetails> consentDetails) {
		this.consentDetails = consentDetails;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getUuId() {
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	public String getConsentToken() {
		return consentToken;
	}
	public void setConsentToken(String consentToken) {
		this.consentToken = consentToken;
	}
	public String getConsentStatus() {
		return consentStatus;
	}
	public void setConsentStatus(String consentStatus) {
		this.consentStatus = consentStatus;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
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
	public String getSelectAll() {
		return selectAll;
	}
	public void setSelectAll(String selectAll) {
		this.selectAll = selectAll;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getConsentId() {
		return consentId;
	}
	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getBankIcon() {
		return bankIcon;
	}
	public void setBankIcon(String bankIcon) {
		this.bankIcon = bankIcon;
	}
	public String getBankLogo() {
		return bankLogo;
	}
	public void setBankLogo(String bankLogo) {
		this.bankLogo = bankLogo;
	}
	public List<AccountsResponse> getAccountDetails() {
		return accountDetails;
	}
	public void setAccountDetails(List<AccountsResponse> accountDetails) {
		this.accountDetails = accountDetails;
	}

	
}
