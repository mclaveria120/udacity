package com.udacity.jwdnd.course1.cloudstorage.dtos;

public class NoteDTO {

	private Long id;
	private String tittle;
	private String description;
	
	
	public NoteDTO() {
	}
	
	public NoteDTO(Long id, String tittle, String description) {
		this.id = id;
		this.tittle = tittle;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
