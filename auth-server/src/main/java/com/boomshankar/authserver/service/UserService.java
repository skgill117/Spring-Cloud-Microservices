package com.boomshankar.authserver.service;

import com.boomshankar.authserver.Dto.UserDto;

public interface UserService {

	UserDto validateAndGetUser(String emailOrPhone, String password);

}
