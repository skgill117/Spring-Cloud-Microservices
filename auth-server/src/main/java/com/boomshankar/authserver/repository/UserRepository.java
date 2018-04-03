package com.boomshankar.authserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boomshankar.authserver.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public Optional<User> findOneByPhone(final String username);
    public Optional<User> findOneByEmail(final String email);

}
