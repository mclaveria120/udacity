package com.udacity.jwdnd.course1.cloudstorage.security;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.exceptions.UserNotFoundExpetion;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user ;
		try{
			 user = userService.getByUsername(username);
		}catch(NoResultException | UserNotFoundExpetion e2){
			throw new UsernameNotFoundException("User not found");
		}   
		CustomeUserDetail userDetail= new CustomeUserDetail(user.getUsername(), user.getPassword(), 
                 true, true, true, true, getGrantedAuthorities(user));
		userDetail.setUser(user);
		return userDetail;
	}

	 private List<GrantedAuthority> getGrantedAuthorities(User user){
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	        return authorities;
	 }
}

