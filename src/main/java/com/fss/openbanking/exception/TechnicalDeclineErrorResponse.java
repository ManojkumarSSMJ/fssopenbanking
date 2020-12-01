package com.fss.openbanking.exception;

public class TechnicalDeclineErrorResponse {
	
		private String code;
		private String error;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

}
