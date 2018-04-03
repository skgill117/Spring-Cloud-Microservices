package com.boomshankar.authserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boomshankar.authserver.repository.UserRoleMappingRepository;
import com.boomshankar.authserver.service.UserRoleMappingService;

@Service
public class UserRoleMappingServiceImpl implements UserRoleMappingService {
	
	@Autowired
	private UserRoleMappingRepository userRoleMappingRepository;

}
