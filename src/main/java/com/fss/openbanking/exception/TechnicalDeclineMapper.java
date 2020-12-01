package com.fss.openbanking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TechnicalDeclineMapper {

	@ExceptionHandler(value = TechnicalDeclineException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<TechnicalDeclineErrorResponse> handleError(TechnicalDeclineException technicalDeclineException) {
		
		TechnicalDeclineErrorResponse ServiceErrorRespose = new TechnicalDeclineErrorResponse(); 
	   	ServiceErrorRespose.setCode(technicalDeclineException.getCode());
	   	ServiceErrorRespose.setError(technicalDeclineException.getMsg());
	    return new ResponseEntity<TechnicalDeclineErrorResponse>(ServiceErrorRespose,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
