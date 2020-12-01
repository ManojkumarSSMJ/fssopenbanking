package com.fss.openbanking.bean;

public class CreditLines {
	
	private String type;
	private BalanceAmount creditLineAmount;
	
	public BalanceAmount getCreditLineAmount() {
		return creditLineAmount;
	}
	public void setCreditLineAmount(BalanceAmount creditLineAmount) {
		this.creditLineAmount = creditLineAmount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
