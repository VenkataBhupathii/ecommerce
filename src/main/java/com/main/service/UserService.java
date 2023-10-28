package com.main.service;

import com.main.exception.UserException;
import com.main.model.User;

public interface UserService {
	
	public User findUserById(Long userId)throws UserException;
	
	public User findUserProfileByJwt(String jwt)throws UserException;
	
}
