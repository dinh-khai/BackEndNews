package com.news.exception;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.news.exception.customerException.FileException;

@ControllerAdvice
public class CustomExceptionController {
	
	/**
	 * handle file exception
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(FileException.class)
	public ResponseEntity<Error> errFileExtension(FileException ex){
	    Error err = new Error(ex.getHttpStatus(),ex.getMsg());
		return new ResponseEntity<Error>(err,ex.getHttpStatus());
	}
	
}
