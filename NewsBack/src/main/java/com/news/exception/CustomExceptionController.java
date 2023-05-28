package com.news.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.news.exception.customException.FileException;

@RestControllerAdvice
public class CustomExceptionController extends ResponseEntityExceptionHandler{
	
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
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	        HttpHeaders headers, HttpStatus status, WebRequest request) {
	    System.out.println("khaidz");
	    Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
}
