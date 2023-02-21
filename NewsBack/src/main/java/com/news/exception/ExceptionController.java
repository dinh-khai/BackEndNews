package com.news.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(MyException.class)
	public ResponseEntity<String> errFileExtension(MyException ex){
		return new ResponseEntity<String>(ex.getErrMessage().getMsg(),ex.getErrMessage().getHttpStatus());
	}
}
