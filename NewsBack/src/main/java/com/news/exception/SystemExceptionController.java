package com.news.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class SystemExceptionController {
    
    /**
     * MethodArgumentTypeMismatchException
     * 
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleSystemException(Exception ex){
        Error err = new Error(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<Error>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Error> handle(MalformedJwtException ex){
        Error err = new Error(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<Error>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
