package com.project.message;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
//@Component
//@ContollerAdvice
public class MessageResponse {

	public MessageResponse(String message) {
		super();
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleMessageResource(Exception exception,WebRequest request){
		ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
	
		return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(RunTimeExceptions.class)
	public ResponseEntity<?> handleRunTimeException(RunTimeExceptions exception,WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
	
		return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
