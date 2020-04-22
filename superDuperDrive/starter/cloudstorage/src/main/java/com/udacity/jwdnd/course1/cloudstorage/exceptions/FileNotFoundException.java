package com.udacity.jwdnd.course1.cloudstorage.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "File not found")
public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public FileNotFoundException() {
	
	}

	public FileNotFoundException(String message) {
	        super(message);
	}
}
