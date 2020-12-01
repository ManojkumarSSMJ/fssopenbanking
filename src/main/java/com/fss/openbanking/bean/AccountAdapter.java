/**
 * 
 */
package com.fss.openbanking.bean;

import java.util.List;

/**
 * @author selvakumara
 *
 */
public class AccountAdapter {

	private String url;
	
	private String customerName;
	
	private String accountNumber;
	
	private String token;
	
	private String amount;
	
	private String remarks;
	
	private String beneficiaryAccountNumber;
	
	private String beneficiaryName;
	
    private String remitterAccountNumber;
    
    private String consentId;
    
    private List<String> selectedConsents;
    
    private String expiryDate;
    
    private String transactionStartDate;
    
    private String transactionEndDate;
    
    private String userUuid;
    
    private String institutionId;
    
    private String callback;
    
    private String mobileNumber;
    
    private AccountRequest accountRequest;
    
    private String paymentData;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getRemitterAccountNumber() {
		return remitterAccountNumber;
	}

	public void setRemitterAccountNumber(String remitterAccountNumber) {
		this.remitterAccountNumber = remitterAccountNumber;
	}

	public String getConsentId() {
		return consentId;
	}

	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}

	public List<String> getSelectedConsents() {
		return selectedConsents;
	}

	public void setSelectedConsents(List<String> selectedConsents) {
		this.selectedConsents = selectedConsents;
	}

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

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public AccountRequest getAccountRequest() {
		return accountRequest;
	}

	public void setAccountRequest(AccountRequest accountRequest) {
		this.accountRequest = accountRequest;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPaymentData() {
		return paymentData;
	}

	public void setPaymentData(String paymentData) {
		this.paymentData = paymentData;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	
}
