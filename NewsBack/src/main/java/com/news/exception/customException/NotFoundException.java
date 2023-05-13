package com.news.exception.customException;
import org.springframework.http.HttpStatus;
import com.news.exception.MyRuntimeException;

public class NotFoundException extends MyRuntimeException{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NotFoundException(HttpStatus status, String msg) {
		super(status, msg);
	}
}
