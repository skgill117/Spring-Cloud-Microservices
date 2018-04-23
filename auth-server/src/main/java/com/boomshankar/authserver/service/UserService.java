package com.boomshankar.authserver.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.boomshankar.authserver.Dto.UserDto;
import com.boomshankar.authserver.model.User;

public interface UserService {

	UserDto validateAndGetUser(String emailOrPhone, String password);

	void createUser(UserDto user) throws NoSuchAlgorithmException, InvalidKeySpecException;

	void updateUser(UserDto user) throws NoSuchAlgorithmException, InvalidKeySpecException;

	List<User> getAllUsers();

}
