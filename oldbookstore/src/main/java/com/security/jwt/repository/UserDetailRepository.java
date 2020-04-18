package com.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.jwt.model.UserDetails;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails, Integer> {
	
	UserDetails findByEmail(String name);
	boolean	existsByEmail(String name);
}
