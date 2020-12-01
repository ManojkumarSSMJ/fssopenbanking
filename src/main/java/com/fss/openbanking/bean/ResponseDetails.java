/**
 * 
 */
package com.fss.openbanking.bean;

/**
 * @author selvakumara
 *
 */
public class ResponseDetails {

	private String responseFlag;

	private String responseMessage;
	
	private String role;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
