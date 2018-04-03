package com.boomshankar.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boomshankar.authserver.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
