package com.fss.openbanking.bean;

public class TppAccountsRequest {

	private String accountId;
	private String bankName;
	private String bankShortName;
	private String bankId;
	private String mobileNumber;
	private String lastUpdateDT;
	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankShortName() {
		return bankShortName;
	}

	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
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

	public String getLastUpdateDT() {
		return lastUpdateDT;
	}

	public void setLastUpdateDT(String lastUpdateDT) {
		this.lastUpdateDT = lastUpdateDT;
	}

}
