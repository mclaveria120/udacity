package com.udacity.jwdnd.course1.cloudstorage.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.udacity.jwdnd.course1.cloudstorage.dtos.CredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.dtos.DTOConverter;
import com.udacity.jwdnd.course1.cloudstorage.dtos.FileDTO;
import com.udacity.jwdnd.course1.cloudstorage.dtos.NoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.dtos.UserDTO;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.services.Util;


@Controller
public class AppController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private NoteService noteService;
     
    @Autowired
    private CredentialService credentialService;
     
    @Autowired
    private FileService fileService;
    
    @GetMapping("/home")
    public String root(Model model, Authentication authentication) {
    	User user = Util.getUser(authentication);
    	
    	List<NoteDTO> notesDTO = noteService.findNotesById(user.getId())
	        		.stream().map(x->DTOConverter.convertNoteToNoteDTO(x))
	        		.collect(Collectors.toList()); 
    	List<CredentialDTO> credentialsDTO = credentialService.findCredentialsById(user.getId())
    			.stream().map(x->DTOConverter.convertCredentialToCredentialDTO(x))
    			.collect(Collectors.toList()); 
    	
    	List<FileDTO> filesDTO = this.fileService.findFilesByUserId(user.getId())
    			.stream().map(x->DTOConverter.converFileToFileDTO(x))
    			.collect(Collectors.toList()); 
    	
    	model.addAttribute("files",filesDTO );
    	model.addAttribute("credentials",credentialsDTO );
    	model.addAttribute("notes",notesDTO );
    	model.addAttribute("newNote", new NoteDTO() );
    	model.addAttribute("newCredential", new CredentialDTO() ); // 
        return "home";
    } 

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    
	@GetMapping("/signup")
	public String sigup(Model model) {
		UserDTO userDTO = new UserDTO();
		model.addAttribute("userDTO", userDTO);
		return "signup";
	}
	
	@PostMapping("/signup")
 	public String signup(UserDTO userDTO) {
		try {
			this.userService.saveUser(this.convertUserDTOToUser(userDTO));	
		}catch(Exception e) {
	 		return "redirect:/signup?error";
		}
 		return "redirect:/login?success";
 	}
	
    private  User convertUserDTOToUser(UserDTO userDTO){
    	User user= new User();
    	BeanUtils.copyProperties(userDTO, user);
    	return user;
    }
}
