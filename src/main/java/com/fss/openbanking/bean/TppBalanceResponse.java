/**
 * 
 */
package com.fss.openbanking.bean;

import java.util.List;

/**
 * @author selvakumara
 *
 */
public class TppBalanceResponse {

	private String responseFlag;

	private String responseMessage;

	private String balance;

	private String accountId;

	private String type;
	
	private String bankName;
	
	private String currency;
	
	private String maskAccountId;
	
	private List<TppBalanceResponse> tppBalanceList;

	public String getResponseFlag() {
		return responseFlag;
	}

	public void setResponseFlag(String responseFlag) {
		this.responseFlag = responseFlag;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TppBalanceResponse> getTppBalanceList() {
		return tppBalanceList;
	}

	public void setTppBalanceList(List<TppBalanceResponse> tppBalanceList) {
		this.tppBalanceList = tppBalanceList;
	}

}
