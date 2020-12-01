package com.fss.openbanking.bean;

import java.sql.Timestamp;

public class LoggingDetails {
	
	private String bankId;
	private String transactionId;
	private String transactionDesc;
	private String transactionRrn;
	private Timestamp reqInitiatedDt;
	private Timestamp respArrivalDt;
	private String reqProcessingDt;
	private String respDataSize;
	private String respCode;
	private String respDesc;
	private String transactionStatus;
	
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getRespDataSize() {
		return respDataSize;
	}
	public void setRespDataSize(String respDataSize) {
		this.respDataSize = respDataSize;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public Timestamp getReqInitiatedDt() {
		return reqInitiatedDt;
	}
	public void setReqInitiatedDt(Timestamp reqInitiatedDt) {
		this.reqInitiatedDt = reqInitiatedDt;
	}
	public Timestamp getRespArrivalDt() {
		return respArrivalDt;
	}
	public void setRespArrivalDt(Timestamp respArrivalDt) {
		this.respArrivalDt = respArrivalDt;
	}
	public String getReqProcessingDt() {
		return reqProcessingDt;
	}
	public void setReqProcessingDt(String reqProcessingDt) {
		this.reqProcessingDt = reqProcessingDt;
	}
	public String getTransactionDesc() {
		return transactionDesc;
	}
	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
	public String getTransactionRrn() {
		return transactionRrn;
	}
	public void setTransactionRrn(String transactionRrn) {
		this.transactionRrn = transactionRrn;
	}

}
