package com.udacity.jwdnd.course1.cloudstorage.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jwdnd.course1.cloudstorage.dtos.DTOConverter;
import com.udacity.jwdnd.course1.cloudstorage.dtos.NoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.Util;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
 
	@Autowired
	private NoteService noteService;
	
 	@PostMapping
	public NoteDTO createNote(NoteDTO noteDTO, Authentication authentication) {
 		Note note = noteService.saveNote(
 				DTOConverter.convertNoteDTOToNote(noteDTO), Util.getUser(authentication).getId());
		return DTOConverter.convertNoteToNoteDTO(note);
 	}
 	
 	@DeleteMapping("/{noteId}")
 	public void deleteNote(@PathVariable long noteId, Authentication authentication) {
 		User user =  Util.getUser(authentication);
 		this.noteService.deleteNote(noteId, user.getId());
 	} 
}