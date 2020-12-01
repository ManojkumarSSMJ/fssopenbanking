package com.fss.openbanking.bean;

public class IsoBankTransactionCode {
	
	private Code domainCode;
	private Code familyCode;
	private Code subFamilyCode;
	private ProprietaryBankTransactionCode transactionHash;
	public Code getDomainCode() {
		return domainCode;
	}
	public void setDomainCode(Code domainCode) {
		this.domainCode = domainCode;
	}
	public Code getFamilyCode() {
		return familyCode;
	}
	public void setFamilyCode(Code familyCode) {
		this.familyCode = familyCode;
	}
	public Code getSubFamilyCode() {
		return subFamilyCode;
	}
	public void setSubFamilyCode(Code subFamilyCode) {
		this.subFamilyCode = subFamilyCode;
	}
	public ProprietaryBankTransactionCode getTransactionHash() {
		return transactionHash;
	}
	public void setTransactionHash(ProprietaryBankTransactionCode transactionHash) {
		this.transactionHash = transactionHash;
	}
	
	

}
