/**
 * 
 */
package com.fss.openbanking.bean;

import java.util.List;

/**
 * @author selvakumara
 *
 */
public class TppStandingOrderResponse {

	private String responseFlag;

	private String responseMessage;

	private List<StandingOrderDetails> standingOrderDetailList;

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

	public List<StandingOrderDetails> getStandingOrderDetailList() {
		return standingOrderDetailList;
	}

	public void setStandingOrderDetailList(
			List<StandingOrderDetails> standingOrderDetailList) {
		this.standingOrderDetailList = standingOrderDetailList;
	}

}
