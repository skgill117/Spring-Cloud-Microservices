package com.boomshankar.authserver.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.boomshankar.authserver.Dto.UserDto;
import com.boomshankar.authserver.exceptions.AuthorizationException;
import com.boomshankar.authserver.exceptions.InvalidPasswordException;
import com.boomshankar.authserver.exceptions.UserAlreadyExistException;
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
				// logger.warn("User is actully exist but done soft delete in past, hence
				// throwing not found exception.");
				throw new UserNotFoundException("User found deleted in past.");
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// Ignoring exceptions with assumption as this will never occur.
			// just log it for better tracking.
			// logger.error(e.getMessage(), e);
		}
		return isValid;
	}

	@Override
	public void createUser(UserDto userDto) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = userRepository.findOneByPhone(userDto.getPhone())
				.orElseGet(() -> userRepository.findOneByEmail(userDto.getEmail()).orElse(null));

		if (user != null) {
			throw new UserAlreadyExistException("User with same phone/email already exist in our list.");
		}

		// TODO: perform basic validation e.g. name format, email format etc.
		performValidation(userDto);

		saveUser(userDto);
	}

	private void saveUser(UserDto userDto) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = new User();
		user.setIsActive(true);
		user.setIsDeleted(false);
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setLastModifiedDatetime(new Date());
		user.setIsPasswordTemporary(true);
		user.setPhone(userDto.getPhone());
		byte[] salt = PasswordHash.generateSalt();
		user.setSalt(salt);
		String passwordHash = PasswordHash.createHash(userDto.getPassword(), salt);
		user.setPasswordHash(passwordHash);
		userRepository.save(user);

	}

	private void performValidation(UserDto userDto) {
		if (StringUtils.isEmpty(userDto.getEmail()) || StringUtils.isEmpty(userDto.getFirstName())) {
			throw new NullPointerException("FirstName and email address cannot be empty.");
		}

	}

	@Override
	public void updateUser(UserDto userDto) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User oldUser = userRepository.getOne(userDto.getId());
		if (oldUser != null) {
			performValidation(userDto);
			oldUser.setIsActive(true);
			oldUser.setIsDeleted(false);
			oldUser.setEmail(userDto.getEmail());
			oldUser.setPhone(userDto.getPhone());
			// oldUser.setFirstName(userData.getFirstName());

			byte[] salt = PasswordHash.generateSalt();
			oldUser.setSalt(salt);

			String passwordHash = PasswordHash.createHash(userDto.getPassword(), salt);
			oldUser.setPasswordHash(passwordHash);

			userRepository.save(oldUser);

		}

	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
