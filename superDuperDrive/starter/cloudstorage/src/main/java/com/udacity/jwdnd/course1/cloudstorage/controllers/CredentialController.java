package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jwdnd.course1.cloudstorage.dtos.CredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.dtos.DTOConverter;
import com.udacity.jwdnd.course1.cloudstorage.entities.Credential;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.Util;

@RestController
@RequestMapping("/api/credentials")
public class CredentialController {

	@Autowired
	private CredentialService credentialService;
	
	
 	@PostMapping
	public CredentialDTO createNote(CredentialDTO credentialDTO, Authentication authentication) {
 		Credential credential = credentialService.saveCredential(
 				DTOConverter.convertCredentialDTOToCredential(credentialDTO), Util.getUser(authentication).getId());
		return DTOConverter.convertCredentialToCredentialDTO(credential);
 	}
 	
 	
 	@DeleteMapping("/{credentialId}")
 	public void deleteNote(@PathVariable long credentialId, Authentication authentication) {
 		User user =  Util.getUser(authentication);
 		this.credentialService.deleteCredential(credentialId, user.getId());
 		
 	}
 	
 	@GetMapping("/{credentialId}/decrypt")
 	public String decryptCredential(@PathVariable long credentialId, Authentication authentication) {
 		User user =  Util.getUser(authentication);
 		return this.credentialService.decriptCredential(credentialId, user.getId());
 	}
}
