package com.fss.openbanking.bean;

import java.util.List;

public class AccountBalances {
	
	private String type;
	private String dateTime;
	private BalanceAmount balanceAmount;
	private String creditLineIncluded;
	private List<CreditLines> creditLines;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public BalanceAmount getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(BalanceAmount balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public String getCreditLineIncluded() {
		return creditLineIncluded;
	}
	public void setCreditLineIncluded(String creditLineIncluded) {
		this.creditLineIncluded = creditLineIncluded;
	}
	public List<CreditLines> getCreditLines() {
		return creditLines;
	}
	public void setCreditLines(List<CreditLines> creditLines) {
		this.creditLines = creditLines;
	}
	
	
}
