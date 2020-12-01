package com.fss.openbanking.bean;

import java.util.List;

public class AccountData {
	
	private String bankId;
	private String bankName;
	private String mobileNumber;
	private String id;
	private String type;
	private String balance;
	private String currency;
	private String usageType;
	private String accountType;
	private String nickname;
	private List<AccountNames> accountNames;
	private List<AccountIdentifications> accountIdentifications;
	private List<AccountBalances> accountBalances;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getUsageType() {
		return usageType;
	}
	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public List<AccountNames> getAccountNames() {
		return accountNames;
	}
	public void setAccountNames(List<AccountNames> accountNames) {
		this.accountNames = accountNames;
	}
	public List<AccountIdentifications> getAccountIdentifications() {
		return accountIdentifications;
	}
	public void setAccountIdentifications(List<AccountIdentifications> accountIdentifications) {
		this.accountIdentifications = accountIdentifications;
	}
	public List<AccountBalances> getAccountBalances() {
		return accountBalances;
	}
	public void setAccountBalances(List<AccountBalances> accountBalances) {
		this.accountBalances = accountBalances;
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	

}
