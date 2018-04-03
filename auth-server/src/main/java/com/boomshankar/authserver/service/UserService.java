package com.boomshankar.authserver.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.boomshankar.authserver.Dto.UserDto;

public interface UserService {

	UserDto validateAndGetUser(String emailOrPhone, String password);

	void createUser(UserDto user) throws NoSuchAlgorithmException, InvalidKeySpecException;

	void updateUser(UserDto user) throws NoSuchAlgorithmException, InvalidKeySpecException;

}
