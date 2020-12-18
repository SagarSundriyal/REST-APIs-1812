package com.appsdeveloperblog.app.webservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.appsdeveloperblog.app.webservices.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler 
{
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) 
	{
		
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null)
			 errorMessageDescription = ex.toString();
		
		@SuppressWarnings("unused")
		ErrorMessage errorMessage = new ErrorMessage(new Date() , ex.getLocalizedMessage());
		
		return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(value = { NullPointerException.class , UserServiceException.class })
	public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request) 
	{
		
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null)
			 errorMessageDescription = ex.toString();
		
		@SuppressWarnings("unused")
		ErrorMessage errorMessage = new ErrorMessage(new Date() , ex.getLocalizedMessage());
		
		return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
/* DON'T NEED  TO add many Exceptions separately we can ADD IN Same 
	@ExceptionHandler(value = { UserServiceException.class })
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) 
	{
		
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null)
			 errorMessageDescription = ex.toString();
		
		@SuppressWarnings("unused")
		ErrorMessage errorMessage = new ErrorMessage(new Date() , ex.getLocalizedMessage());
		
		return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
*/