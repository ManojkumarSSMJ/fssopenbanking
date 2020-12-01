package com.fss.openbanking.bean;

public class Balance {
	
	private String type;
	private BalanceAmount balanceAmount;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BalanceAmount getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(BalanceAmount balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

}
