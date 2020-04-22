package com.udacity.jwdnd.course1.cloudstorage.services;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jwdnd.course1.cloudstorage.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.exceptions.FileNameNoValidException;
import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;

@Service
@Transactional
public class FileService {

	@Autowired
	private FileMapper fileMapper;
	
	public File saveFile(File file, Long userId){
		File fileMatchedByName = this.fileMapper.findByName(file.getName());
		if(fileMatchedByName != null)  {
			throw new FileNameNoValidException();
		}
		 this.fileMapper.save(file,userId);
		 return file;
	}
	
	public List<File> findFilesByUserId(long userId){
		return this.fileMapper.findAllByUserId(userId);
	}
	
	public void deleteFile(long fileId, long userId) {
		this.fileMapper.deleteByIdAndUserId(fileId,userId);
	}
	
	public Blob convertToBlob(byte[] bytes) throws SQLException{
		return new javax.sql.rowset.serial.SerialBlob(bytes);
	}

	public File getById(long fileId, long userId) {
		File file = this.fileMapper.findByIdAndUserId(fileId,userId);
		return file;
	}
}
