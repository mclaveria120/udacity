package com.udacity.jwdnd.course1.cloudstorage.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "FileName not valid")
public class FileNameNoValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public FileNameNoValidException() {
	
	}

	public FileNameNoValidException(String message) {
	        super(message);
	}
}
