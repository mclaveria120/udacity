package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.exceptions.UserAlreadyRegisterException;
import com.udacity.jwdnd.course1.cloudstorage.exceptions.UserNotFoundExpetion;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;
	 
	public void saveUser(User user) {
			if(this.userMapper.findByUsername(user.getUsername())==null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				this.userMapper.save(user);
			}else{
				throw new UserAlreadyRegisterException();
			}; 
	}

	public User getByUsername(String username){
		User user = this.userMapper.findByUsername(username);
		if(user == null) {
			throw new UserNotFoundExpetion();
		}
		return user;
	}
}
