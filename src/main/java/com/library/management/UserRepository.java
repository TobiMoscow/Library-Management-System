package com.library.management;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findUserByName(String name);
	
	Optional<User> findUserByEmail(String email);

}
