package com.fss.openbanking.bean;

public class AccountRequest {

	private String expiresAt;
    
    private String transactionFrom;
    
    private String transactionTo;
    
    private String[] featureScope;

	public String getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(String expiresAt) {
		this.expiresAt = expiresAt;
	}

	public String getTransactionFrom() {
		return transactionFrom;
	}

	public void setTransactionFrom(String transactionFrom) {
		this.transactionFrom = transactionFrom;
	}

	public String getTransactionTo() {
		return transactionTo;
	}

	public void setTransactionTo(String transactionTo) {
		this.transactionTo = transactionTo;
	}

	public String[] getFeatureScope() {
		return featureScope;
	}

	public void setFeatureScope(String[] featureScope) {
		this.featureScope = featureScope;
	}
    
    
    
}
