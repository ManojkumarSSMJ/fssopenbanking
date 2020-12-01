package com.fss.openbanking.bean;

import java.util.List;

public class StandingOrderDetails {

	private String accountId;
	private String bankId;
	private String mobileNumber;
	private String maskAccountId;
	private String standingOrderId;
	private String frequency;
	private String beneficiaryName;
	private String firstPaymentDate;
	private String firstPaymentAmount;
	private String nextPaymentDate;
	private String nextPaymentAmount;
	private String finalPaymentDate;
	private String finalPaymentAmount;
	private String standingOrderStatus;
	private List<AccountIdentifications> standingOrderIdentifications;

	public String getStandingOrderId() {
		return standingOrderId;
	}

	public void setStandingOrderId(String standingOrderId) {
		this.standingOrderId = standingOrderId;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFirstPaymentDate() {
		return firstPaymentDate;
	}

	public void setFirstPaymentDate(String firstPaymentDate) {
		this.firstPaymentDate = firstPaymentDate;
	}

	public String getFirstPaymentAmount() {
		return firstPaymentAmount;
	}

	public void setFirstPaymentAmount(String firstPaymentAmount) {
		this.firstPaymentAmount = firstPaymentAmount;
	}

	public String getNextPaymentDate() {
		return nextPaymentDate;
	}

	public void setNextPaymentDate(String nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	public String getNextPaymentAmount() {
		return nextPaymentAmount;
	}

	public void setNextPaymentAmount(String nextPaymentAmount) {
		this.nextPaymentAmount = nextPaymentAmount;
	}

	public String getFinalPaymentDate() {
		return finalPaymentDate;
	}

	public void setFinalPaymentDate(String finalPaymentDate) {
		this.finalPaymentDate = finalPaymentDate;
	}

	public String getFinalPaymentAmount() {
		return finalPaymentAmount;
	}

	public void setFinalPaymentAmount(String finalPaymentAmount) {
		this.finalPaymentAmount = finalPaymentAmount;
	}

	public String getStandingOrderStatus() {
		return standingOrderStatus;
	}

	public void setStandingOrderStatus(String standingOrderStatus) {
		this.standingOrderStatus = standingOrderStatus;
	}

	public List<AccountIdentifications> getStandingOrderIdentifications() {
		return standingOrderIdentifications;
	}

	public void setStandingOrderIdentifications(List<AccountIdentifications> standingOrderIdentifications) {
		this.standingOrderIdentifications = standingOrderIdentifications;
	}

	public String getMaskAccountId() {
		return maskAccountId;
	}

	public void setMaskAccountId(String maskAccountId) {
		this.maskAccountId = maskAccountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
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

	
}
