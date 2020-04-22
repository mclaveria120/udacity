package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credential;
import com.udacity.jwdnd.course1.cloudstorage.exceptions.CredentialNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;

@Service
@Transactional
public class CredentialService {

	@Autowired
	private CredentialMapper credentialMapper;
	
	@Autowired
	private EncryptionService encryptionService;
	 
	
	public Credential saveCredential(Credential credential, Long userId){
		if(credential.getId()!=null) {
			Credential oldCredential = this.credentialMapper.findByIdAndUserId(credential.getId(), userId);
			if(oldCredential!=null) {
				updatePassword(credential, oldCredential);
				this.credentialMapper.updateCredential(credential);	
			}else {
				throw new CredentialNotFoundException();
			}
		}else {
			setNewPassword(credential);
			this.credentialMapper.save(credential,userId);	
		}
		return credential;
	}

	private void updatePassword(Credential credential, Credential oldCredential) {
		if(!this.encryptionService.decryptValue(oldCredential.getPassword(),oldCredential.getKey()).equals(credential.getPassword())){
			setNewPassword(credential);
		}else {
			credential.setPassword(oldCredential.getPassword());
			credential.setKey(oldCredential.getKey());
		}
	}

	private void setNewPassword(Credential credential) {
		String secretKey = this.generateSecretKey();
		credential.setPassword(this.encryptionService.encryptValue(credential.getPassword(), secretKey));
		credential.setKey(secretKey);
	}
	
	public List<Credential> findCredentialsById(long userId){
		return this.credentialMapper.findAllByUserId(userId);
	}
	
	public void deleteCredential(long credentialId, long userId) {
		this.credentialMapper.deleteByIdAndUserId(credentialId,userId);
	}
	
	public String decriptCredential(long credentialId, Long userId) {
		Credential credential = this.credentialMapper.findByIdAndUserId(credentialId, userId);
		return this.encryptionService.decryptValue(credential.getPassword(),credential.getKey());
	}
	
	
	private String generateSecretKey() {
		SecureRandom random = new SecureRandom();
		byte[] key = new byte[16];
		random.nextBytes(key);
		return  Base64.getEncoder().encodeToString(key);
	}
}
