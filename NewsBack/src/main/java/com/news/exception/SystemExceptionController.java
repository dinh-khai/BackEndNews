package com.news.exception;
import java.sql.SQLException;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class SystemExceptionController {
	
	/**
	 * MethodArgumentTypeMismatchException
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Error> errFileExtension(MethodArgumentTypeMismatchException ex){
		Error err = new Error(HttpStatus.NOT_FOUND,ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<Error>(err ,HttpStatus.NOT_FOUND);
	}
	
	/**
	 * handle sql exception
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Error> sqlException(SQLException ex){
		Error err=new Error(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<Error>(err,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
