package com.udacity.jwdnd.course1.cloudstorage.dtos;

public class UserDTO {

	private String username;
	private String password;
	private String firstName;
	private String lastname;

	public UserDTO() {
		
	}
	
	public UserDTO(String username, String password, String firstName, String lastname) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastname = lastname;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
