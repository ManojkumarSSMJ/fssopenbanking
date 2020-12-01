package com.fss.openbanking.bean;

import java.util.List;

public class PayeeDetails {
	
	private String name;
	
	private List<AccountIdentifications> accountIdentifications;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AccountIdentifications> getAccountIdentifications() {
		return accountIdentifications;
	}

	public void setAccountIdentifications(List<AccountIdentifications> accountIdentifications) {
		this.accountIdentifications = accountIdentifications;
	}

}
