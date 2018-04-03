package com.boomshankar.authserver.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boomshankar.authserver.Dto.UserDto;
import com.boomshankar.authserver.exceptions.AuthorizationException;
import com.boomshankar.authserver.exceptions.InvalidPasswordException;
import com.boomshankar.authserver.exceptions.UserNotFoundException;
import com.boomshankar.authserver.mapper.UserDtoMapper;
import com.boomshankar.authserver.model.User;
import com.boomshankar.authserver.repository.UserRepository;
import com.boomshankar.authserver.security.PasswordHash;
import com.boomshankar.authserver.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDtoMapper userDdtoMapper;

	@Override
	public UserDto validateAndGetUser(String emailOrPhone, String password) {
		User user = null;

		user = userRepository.findOneByEmail(emailOrPhone).orElseGet(
				() -> userRepository.findOneByPhone(emailOrPhone).orElseThrow(() -> new UserNotFoundException(
						String.format("User with phone/email [%s] not found in database.", emailOrPhone))));

		validateUserAgainstLocalRepository(user, password);

		return userDdtoMapper.transformToUserDto(user);
	}

	private boolean validateUserAgainstLocalRepository(User user, String password) {
		boolean isValid = false;
		try {
			isValid = PasswordHash.validatePassword(password, user.getPasswordHash());
			if (!isValid) {
				throw new InvalidPasswordException("User password is invalid.");
			}
			if (!user.isIsActive()) {
				throw new AuthorizationException("User is disabled/locked in our database.");
			}
			if (user.isIsDeleted()) {
				//logger.warn("User is actully exist but done soft delete in past, hence throwing not found exception.");
				throw new UserNotFoundException("User found deleted in past.");
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// Ignoring exceptions with assumption as this will never occur.
			// just log it for better tracking.
			//logger.error(e.getMessage(), e);
		}
		return isValid;
	}

}
