package com.fss.openbanking.bean;

public class TransactionData {
	
	private String accountId;
	private String bankName;
	private String bankId;
	private String id;
	private String reference;
	private String date;
	private String bookingDateTime;
	private String status;
	private String description;
	private String amount;
	private String currency;
	private String[] transactionInformation;
	private String links;
	private TransactionAmount transactionAmount;
	private Balance balance;
	private ProprietaryBankTransactionCode proprietaryBankTransactionCode;
	private IsoBankTransactionCode isoBankTransactionCode;
	private IsoBankTransactionCode enrichment;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBookingDateTime() {
		return bookingDateTime;
	}
	public void setBookingDateTime(String bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String[] getTransactionInformation() {
		return transactionInformation;
	}
	public void setTransactionInformation(String[] transactionInformation) {
		this.transactionInformation = transactionInformation;
	}
	public String getLinks() {
		return links;
	}
	public void setLinks(String links) {
		this.links = links;
	}
	public TransactionAmount getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(TransactionAmount transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Balance getBalance() {
		return balance;
	}
	public void setBalance(Balance balance) {
		this.balance = balance;
	}
	public ProprietaryBankTransactionCode getProprietaryBankTransactionCode() {
		return proprietaryBankTransactionCode;
	}
	public void setProprietaryBankTransactionCode(ProprietaryBankTransactionCode proprietaryBankTransactionCode) {
		this.proprietaryBankTransactionCode = proprietaryBankTransactionCode;
	}
	public IsoBankTransactionCode getIsoBankTransactionCode() {
		return isoBankTransactionCode;
	}
	public void setIsoBankTransactionCode(IsoBankTransactionCode isoBankTransactionCode) {
		this.isoBankTransactionCode = isoBankTransactionCode;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public IsoBankTransactionCode getEnrichment() {
		return enrichment;
	}
	public void setEnrichment(IsoBankTransactionCode enrichment) {
		this.enrichment = enrichment;
	}
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
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	

}
