/**
 * 
 */
package com.fss.openbanking.bean;

/**
 * @author selvakumara
 *
 */
public class BeneficiaryDetails {

	private String remitterAccountNumber;
	
	private String beneficiaryName;
	
	private String beneficiaryAccountNumber;
	
	private String ifscCode;
	
	private String remitterMaskAccountNumber;
	
	private String beneficiaryMaskAccountNumber;
	
	private String clientId;

	public String getRemitterAccountNumber() {
		return remitterAccountNumber;
	}

	public void setRemitterAccountNumber(String remitterAccountNumber) {
		this.remitterAccountNumber = remitterAccountNumber;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getRemitterMaskAccountNumber() {
		return remitterMaskAccountNumber;
	}

	public void setRemitterMaskAccountNumber(String remitterMaskAccountNumber) {
		this.remitterMaskAccountNumber = remitterMaskAccountNumber;
	}

	public String getBeneficiaryMaskAccountNumber() {
		return beneficiaryMaskAccountNumber;
	}

	public void setBeneficiaryMaskAccountNumber(String beneficiaryMaskAccountNumber) {
		this.beneficiaryMaskAccountNumber = beneficiaryMaskAccountNumber;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	
}
