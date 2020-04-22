package com.udacity.jwdnd.course1.cloudstorage.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Note could not be modified")
public class NoteCouldNotBeModifiedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoteCouldNotBeModifiedException() {
	
	}

	public NoteCouldNotBeModifiedException(String message) {
	        super(message);
	}
}
