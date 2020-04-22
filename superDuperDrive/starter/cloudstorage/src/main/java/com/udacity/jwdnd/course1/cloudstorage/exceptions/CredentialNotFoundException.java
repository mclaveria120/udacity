package com.udacity.jwdnd.course1.cloudstorage.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Credential not found")
public class CredentialNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public CredentialNotFoundException() {
	
	}

	public CredentialNotFoundException(String message) {
	        super(message);
	}
}
