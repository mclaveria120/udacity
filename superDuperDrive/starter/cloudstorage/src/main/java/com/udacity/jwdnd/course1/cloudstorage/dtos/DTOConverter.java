package com.udacity.jwdnd.course1.cloudstorage.dtos;

import org.springframework.beans.BeanUtils;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credential;
import com.udacity.jwdnd.course1.cloudstorage.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.entities.Note;

public class DTOConverter {

	
    public static  Note convertNoteDTOToNote(NoteDTO noteDTO){
    	Note note = new Note();
    	BeanUtils.copyProperties(noteDTO, note);
    	return note;
    } 
    public static NoteDTO convertNoteToNoteDTO(Note note){
    	NoteDTO noteDTO = new NoteDTO();
    	BeanUtils.copyProperties(note, noteDTO);
    	return noteDTO;
    }
	public static Credential convertCredentialDTOToCredential(CredentialDTO credentialDTO) {
		Credential credential = new Credential();
    	BeanUtils.copyProperties(credentialDTO, credential);
    	return credential;
	}
	public static CredentialDTO convertCredentialToCredentialDTO(Credential credential) {
		CredentialDTO credentialDTO = new CredentialDTO();
    	BeanUtils.copyProperties(credential, credentialDTO);
    	return credentialDTO;
	}
	public static FileDTO  converFileToFileDTO(File file) {
		return   new FileDTO(file.getName(), file.getId());
	}
}
