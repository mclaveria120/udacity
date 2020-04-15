package com.udacity.jdnd.course3.critter.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Employee not found")
public class EmployeeNotFoundExpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public EmployeeNotFoundExpetion() {
	
	}

	public EmployeeNotFoundExpetion(String message) {
	        super(message);
	}
}
