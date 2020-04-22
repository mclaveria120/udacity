package com.udacity.jwdnd.course1.cloudstorage.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Note not found")
public class NoteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoteNotFoundException() {
	
	}

	public NoteNotFoundException(String message) {
	        super(message);
	}
}
