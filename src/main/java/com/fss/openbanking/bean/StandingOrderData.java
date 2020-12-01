package com.fss.openbanking.bean;

public class StandingOrderData {
	
	private String id;
	private StatusDetails statusDetails;
	private String reference;
	private PayeeDetails payeeDetails;	
	private TransactionAmount firstPaymentAmount;
	private String firstPaymentDateTime;
	private TransactionAmount nextPaymentAmount;
	private String nextPaymentDateTime;
	private TransactionAmount finalPaymentAmount;
	private String finalPaymentDateTime;
	private Frequency frequency;
	private String accountId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public StatusDetails getStatusDetails() {
		return statusDetails;
	}
	public void setStatusDetails(StatusDetails statusDetails) {
		this.statusDetails = statusDetails;
	}
	public PayeeDetails getPayeeDetails() {
		return payeeDetails;
	}
	public void setPayeeDetails(PayeeDetails payeeDetails) {
		this.payeeDetails = payeeDetails;
	}
	public String getFirstPaymentDateTime() {
		return firstPaymentDateTime;
	}
	public void setFirstPaymentDateTime(String firstPaymentDateTime) {
		this.firstPaymentDateTime = firstPaymentDateTime;
	}
	public String getNextPaymentDateTime() {
		return nextPaymentDateTime;
	}
	public void setNextPaymentDateTime(String nextPaymentDateTime) {
		this.nextPaymentDateTime = nextPaymentDateTime;
	}
	public String getFinalPaymentDateTime() {
		return finalPaymentDateTime;
	}
	public void setFinalPaymentDateTime(String finalPaymentDateTime) {
		this.finalPaymentDateTime = finalPaymentDateTime;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Frequency getFrequency() {
		return frequency;
	}
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public TransactionAmount getFirstPaymentAmount() {
		return firstPaymentAmount;
	}
	public void setFirstPaymentAmount(TransactionAmount firstPaymentAmount) {
		this.firstPaymentAmount = firstPaymentAmount;
	}
	public TransactionAmount getNextPaymentAmount() {
		return nextPaymentAmount;
	}
	public void setNextPaymentAmount(TransactionAmount nextPaymentAmount) {
		this.nextPaymentAmount = nextPaymentAmount;
	}
	public TransactionAmount getFinalPaymentAmount() {
		return finalPaymentAmount;
	}
	public void setFinalPaymentAmount(TransactionAmount finalPaymentAmount) {
		this.finalPaymentAmount = finalPaymentAmount;
	}

}
