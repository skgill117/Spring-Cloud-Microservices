package com.boomshankar.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boomshankar.authserver.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
