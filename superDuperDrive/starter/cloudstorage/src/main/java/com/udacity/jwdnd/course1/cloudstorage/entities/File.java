package com.udacity.jwdnd.course1.cloudstorage.entities;

import java.sql.Blob;

import javax.persistence.*;

@Entity
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String type;
	private String fileSize;

	@Lob
    @Column(name = "data", columnDefinition="BLOB")
	private byte[] data;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public File(Long id, String name, String type, String fileSize, byte[] data, User user) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.fileSize = fileSize;
		this.data=data;
		this.user = user;
	}

	public File() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
