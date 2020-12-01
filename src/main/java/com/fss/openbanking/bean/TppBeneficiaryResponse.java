/**
 * 
 */
package com.fss.openbanking.bean;

import java.util.List;

/**
 * @author selvakumara
 *
 */
public class TppBeneficiaryResponse {

	private String responseFlag;

	private String responseMessage;

	private List<BeneficiaryDetails> beneficiaryDetails;

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

	public List<BeneficiaryDetails> getBeneficiaryDetails() {
		return beneficiaryDetails;
	}

	public void setBeneficiaryDetails(
			List<BeneficiaryDetails> beneficiaryDetails) {
		this.beneficiaryDetails = beneficiaryDetails;
	}

}
