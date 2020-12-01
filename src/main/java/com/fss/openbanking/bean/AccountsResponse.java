package com.fss.openbanking.bean;

import java.util.List;

public class AccountsResponse {

	private String accountHolderName;
	private List<AccountIdentifications> accountIdentification;
	private String accountId;
	private String currencyType;
	private String creditDebitIndicator;
	private String bankName;
	private String accountType;
	private String accountSubType;
	private String balance;
	private String bankShortName;
	private String maskAccountId;
	private String bankId;
	private String mobileNumber;
	private List<ConsentDetails> consentDetails;
	private String logoUrl;
	private String iconUrl;
	
	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCreditDebitIndicator() {
		return creditDebitIndicator;
	}

	public void setCreditDebitIndicator(String creditDebitIndicator) {
		this.creditDebitIndicator = creditDebitIndicator;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountSubType() {
		return accountSubType;
	}

	public void setAccountSubType(String accountSubType) {
		this.accountSubType = accountSubType;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBankShortName() {
		return bankShortName;
	}

	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}

	public List<ConsentDetails> getConsentDetails() {
		return consentDetails;
	}

	public void setConsentDetails(List<ConsentDetails> consentDetails) {
		this.consentDetails = consentDetails;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public List<AccountIdentifications> getAccountIdentification() {
		return accountIdentification;
	}

	public void setAccountIdentification(List<AccountIdentifications> accountIdentification) {
		this.accountIdentification = accountIdentification;
	}

	public String getMaskAccountId() {
		return maskAccountId;
	}

	public void setMaskAccountId(String maskAccountId) {
		this.maskAccountId = maskAccountId;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

}
