package com.boomshankar.authserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boomshankar.authserver.Dto.UserDto;
import com.boomshankar.authserver.exceptions.AuthorizationException;
import com.boomshankar.authserver.exceptions.InvalidPasswordException;
import com.boomshankar.authserver.exceptions.UserAlreadyExistException;
import com.boomshankar.authserver.exceptions.UserNotFoundException;
import com.boomshankar.authserver.service.UserService;

@RestController
public class AuthController {
	

	@Autowired
	private UserService userService;

	/**
	 *
	 * This method validates user credentials at Local database level based on
	 * passed parameters.
	 *
	 * Use this method for portal level authentication.
	 *
	 * @param username
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	@RequestMapping(value = "/getUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> getUser(@RequestParam(value = "emailOrPhone") String emailOrPhone,
			@RequestParam(value = "password") String password) throws UserNotFoundException {
		return validateAndGetUser(emailOrPhone, password);
	}
	
	
    @RequestMapping(value = "/create-user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UserDto user) {
    	ResponseEntity<String> responseEntity = null;
    	try{
    	 userService.createUser(user);
    	 responseEntity = new ResponseEntity<String>("user created successfully", HttpStatus.OK);
    		
    	} catch (Exception ex) {
    		responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    
    @RequestMapping(value = "/update-user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@RequestBody UserDto user) {
    	ResponseEntity<String> responseEntity = null;
    	try{
    	 userService.updateUser(user);
    	 responseEntity = new ResponseEntity<String>("user updated successfully", HttpStatus.OK);
    		
    	} catch (Exception ex) {
    		responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

	private ResponseEntity<UserDto> validateAndGetUser(String emailOrPhone, String password) {
		ResponseEntity<UserDto> responseEntity = null;
		UserDto user = null;
		try {
			user = userService.validateAndGetUser(emailOrPhone, password);
			responseEntity = new ResponseEntity<UserDto>(user, HttpStatus.OK);
		} catch (Exception ex) {
			responseEntity = handleUserException(user, ex);
		}
		return responseEntity;
	}

	/**
	 * Creating response based on different exception thrown by different layers of
	 * application.
	 *
	 * @param user
	 * @param ex
	 * @return
	 */
	private ResponseEntity<UserDto> handleUserException(UserDto user, Exception ex) {
		ResponseEntity<UserDto> responseEntity;
		if (ex instanceof UserNotFoundException) {
			responseEntity = new ResponseEntity<UserDto>(user, HttpStatus.NOT_FOUND);
		} else if (ex instanceof AuthorizationException) {
			responseEntity = new ResponseEntity<UserDto>(user, HttpStatus.UNAUTHORIZED);
		} else if (ex instanceof UserAlreadyExistException) {
			responseEntity = new ResponseEntity<UserDto>(user, HttpStatus.FOUND);
		} else if (ex instanceof InvalidPasswordException) {
			responseEntity = new ResponseEntity<UserDto>(user, HttpStatus.FORBIDDEN);
		} else {
			responseEntity = new ResponseEntity<UserDto>(user, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		}
		user = new UserDto();
		user.setErrorMessage(ex.getMessage());
		// logger.error(ex.getMessage(), ex);
		return responseEntity;
	}
}
