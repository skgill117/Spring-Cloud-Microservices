package com.boomshankar.authserver.mapper;

import org.springframework.stereotype.Service;

import com.boomshankar.authserver.Dto.UserDto;
import com.boomshankar.authserver.model.User;

@Service
public class UserDtoMapper {

	public UserDto transformToUserDto(User user) {
		UserDto userDto = null;
		if (null != user) {
			userDto = new UserDto();
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			userDto.setEmail(user.getEmail());
			userDto.setPhone(user.getPhone());
		}

		return userDto;

	}
}
