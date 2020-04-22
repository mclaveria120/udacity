package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.exceptions.NoteCouldNotBeModifiedException;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;

@Service
@Transactional
public class NoteService {

	@Autowired 
	private NoteMapper noteMapper;
	
	public Note saveNote(Note note, Long userId){
		if(note.getId()!=null) {
			if(this.noteMapper.findByIdAndUserId(note.getId(), userId)!=null) {
				this.noteMapper.updateNote(note);	
			}else {
				throw new NoteCouldNotBeModifiedException();
			}
		}else {
			this.noteMapper.save(note,userId);	
		}
		return note;
	}
	
	public List<Note> findNotesById(long userId){
		return this.noteMapper.findAllByUserId(userId);
	}
	
	public void deleteNote(long noteId, long userId) {
		this.noteMapper.deleteByIdAndUserId(noteId, userId);
	}
	
	
}


