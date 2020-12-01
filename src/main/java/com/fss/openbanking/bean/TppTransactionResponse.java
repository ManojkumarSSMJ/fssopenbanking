/**
 * 
 */
package com.fss.openbanking.bean;

import java.util.List;

/**
 * @author selvakumara
 *
 */
public class TppTransactionResponse {

	private List<TransactionDetails> transactionDetailList;

	private String responseFlag;

	private String responseMessage;

	public List<TransactionDetails> getTransactionDetailList() {
		return transactionDetailList;
	}

	public void setTransactionDetailList(
			List<TransactionDetails> transactionDetailList) {
		this.transactionDetailList = transactionDetailList;
	}

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

}
