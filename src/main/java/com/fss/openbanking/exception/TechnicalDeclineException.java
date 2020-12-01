package com.fss.openbanking.exception;

public class TechnicalDeclineException extends RuntimeException {

	private static final long serialVersionUID = -6619591285699335408L;

	private String code;

	private String msg;

	public TechnicalDeclineException(String msg, String code) {
		this.msg = msg;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
