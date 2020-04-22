package com.udacity.jwdnd.course1.cloudstorage.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserAlreadyRegisterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public UserAlreadyRegisterException() {
	
	}

	public UserAlreadyRegisterException(String message) {
	        super(message);
	}
}
