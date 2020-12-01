/**
 * 
 */
package com.fss.openbanking.bean;

/**
 * @author selvakumara
 *
 */
public class UserDetails {

	private String userId;
	private String userName;
	private String role;
	private String activationStatus;
	private String mobileNumber;
	private String last4DigitMobNo;
	private String paymentData;
	private String AccountData;
	private String consentTokenFlag;
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(String activationStatus) {
		this.activationStatus = activationStatus;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getLast4DigitMobNo() {
		return last4DigitMobNo;
	}

	public void setLast4DigitMobNo(String last4DigitMobNo) {
		this.last4DigitMobNo = last4DigitMobNo;
	}

	public String getPaymentData() {
		return paymentData;
	}

	public void setPaymentData(String paymentData) {
		this.paymentData = paymentData;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getConsentTokenFlag() {
		return consentTokenFlag;
	}

	public void setConsentTokenFlag(String consentTokenFlag) {
		this.consentTokenFlag = consentTokenFlag;
	}

	public String getAccountData() {
		return AccountData;
	}

	public void setAccountData(String accountData) {
		AccountData = accountData;
	}

}
