package com.udacity.jdnd.course3.critter.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Customer not found")
public class CustomerNotFoundExpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public CustomerNotFoundExpetion() {
	
	}

	public CustomerNotFoundExpetion(String message) {
	        super(message);
	}
}
