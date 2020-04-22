package com.udacity.jwdnd.course1.cloudstorage.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundExpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public UserNotFoundExpetion() {
	
	}

	public UserNotFoundExpetion(String message) {
	        super(message);
	}
}
