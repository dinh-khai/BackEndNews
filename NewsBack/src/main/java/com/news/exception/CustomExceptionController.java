package com.news.exception;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.news.exception.customException.FileException;

@RestControllerAdvice
public class CustomExceptionController {
	
	/**
	 * handle file exception
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(FileException.class)
	public ResponseEntity<Error> errFileExtension(FileException ex){
	    Error err = new Error(new Date(),ex.getHttpStatus().value(),ex.getMsg());
		return new ResponseEntity<Error>(err,ex.getHttpStatus());
	}
//	
//	@ExceptionHandler(NotFoundException.class)
//	public ResponseEntity<Error> errNotFound(NotFoundException ex){
//	    Error err = new Error(new Date(),ex.getStatus(),ex.getMsg());
//		return new ResponseEntity<Error>(err,ex.getStatus());
//	}
	
    /**
     * 
     */
	@ExceptionHandler(MyException.class)
    public ResponseEntity<Error> handleException(MyException ex){
        Error err = new Error(new Date(),ex.getStatus().value(),ex.getMsg());
        return new ResponseEntity<Error>(err,ex.getStatus());
    }
}
