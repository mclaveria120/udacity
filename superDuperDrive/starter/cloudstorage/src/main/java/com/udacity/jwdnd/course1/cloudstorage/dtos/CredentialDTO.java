package com.udacity.jwdnd.course1.cloudstorage.dtos;

public class CredentialDTO {

	private Long id;
	private String url;
	private String username;
	private String password;
	
	public CredentialDTO() {
		
	}
	public CredentialDTO(Long id, String url, String username, String password) {
		this.id = id;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
