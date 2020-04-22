package com.udacity.jwdnd.course1.cloudstorage.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;


public class CustomeUserDetail extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;

	private User user;
	
	public CustomeUserDetail(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
