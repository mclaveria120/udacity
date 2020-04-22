package com.udacity.jwdnd.course1.cloudstorage.controllers;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.dtos.FileDTO;
import com.udacity.jwdnd.course1.cloudstorage.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.Util;

@RestController
@RequestMapping("/api/files")
public class FileController {

	@Autowired
	private FileService fileService;
	
	@PostMapping
	public FileDTO createFile(@RequestParam("file") MultipartFile multipartFile,Authentication authentication) {
		User user =  Util.getUser(authentication);
		File file =this.fileService.saveFile(this.convertMultipartFileToFile(multipartFile), user.getId());
		return new FileDTO(file.getName(), file.getId());
	}
	
	@GetMapping("/{fileId}")
	public ResponseEntity<Resource> getFile(@PathVariable Long fileId, Authentication authentication) throws SQLException {
		User user =  Util.getUser(authentication);
		File file = this.fileService.getById(fileId, user.getId());
		return ResponseEntity.ok()
			                .contentType(MediaType.parseMediaType(file.getType()))
			                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
			                .body(new ByteArrayResource(file.getData().getBytes(1, (int)file.getData().length())));
		
	}
	
 	
 	@DeleteMapping("/{fileId}")
 	public void deleteFile(@PathVariable long fileId, Authentication authentication) {
 		User user =  Util.getUser(authentication);
 		this.fileService.deleteFile(fileId, user.getId());
 		
 	}
 	
 	private File convertMultipartFileToFile(MultipartFile multipartFile) {
		File file = new File();
			file.setName(multipartFile.getOriginalFilename());
			file.setType(multipartFile.getContentType());
			file.setFileSize(multipartFile.getSize()+"");
			try {
				file.setData( this.fileService.convertToBlob(multipartFile.getBytes()));
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
		return file;
 	}
 	
}
