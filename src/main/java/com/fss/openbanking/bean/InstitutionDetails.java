package com.fss.openbanking.bean;

import java.util.List;

public class InstitutionDetails {
	
	private String bankId;

	private String bankName;
	
	private String bankFullName;
	
	private String bankCountry;
	
	private String bankLogo;
	
	private String bankIcon;
	
	private String state;
	
	private String nonce;
	
	private String lastUpdateDT;

	private String mobileNumber;

	private List<ConsentDetails> consentDetails;

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankFullName() {
		return bankFullName;
	}

	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}

	public String getBankCountry() {
		return bankCountry;
	}

	public void setBankCountry(String bankCountry) {
		this.bankCountry = bankCountry;
	}

	public String getBankLogo() {
		return bankLogo;
	}

	public void setBankLogo(String bankLogo) {
		this.bankLogo = bankLogo;
	}

	public String getBankIcon() {
		return bankIcon;
	}

	public void setBankIcon(String bankIcon) {
		this.bankIcon = bankIcon;
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

	public String getLastUpdateDT() {
		return lastUpdateDT;
	}

	public void setLastUpdateDT(String lastUpdateDT) {
		this.lastUpdateDT = lastUpdateDT;
	}
	
	
}
