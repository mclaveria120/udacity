package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.security.core.Authentication;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.security.CustomeUserDetail;

public class Util {

	public static User getUser( Authentication authentication) {
		CustomeUserDetail customerUserDetail = (CustomeUserDetail)authentication.getPrincipal();
		return customerUserDetail.getUser();
	}
	
}
