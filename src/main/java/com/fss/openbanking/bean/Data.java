package com.fss.openbanking.bean;

public class Data {

	private String id;
	private String deleteStatus;
	private String creationDate;
	private String[] userConsents;
	private String type;
	private String balance;
	private String currency;
	private String usageType;
	private String accountType;
	private String nickname;
	private BalanceAmount mainBalanceAmount;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String[] getUserConsents() {
		return userConsents;
	}
	public void setUserConsents(String[] userConsents) {
		this.userConsents = userConsents;
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
	public BalanceAmount getMainBalanceAmount() {
		return mainBalanceAmount;
	}
	public void setMainBalanceAmount(BalanceAmount mainBalanceAmount) {
		this.mainBalanceAmount = mainBalanceAmount;
	}

}
