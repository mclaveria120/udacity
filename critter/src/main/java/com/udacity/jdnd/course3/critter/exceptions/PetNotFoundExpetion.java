package com.udacity.jdnd.course3.critter.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pet not found")
public class PetNotFoundExpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public PetNotFoundExpetion() {
	
	}

	public PetNotFoundExpetion(String message) {
	        super(message);
	}
}
