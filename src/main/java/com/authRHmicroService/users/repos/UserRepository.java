package com.authRHmicroService.users.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authRHmicroService.users.entities.User;
import com.authRHmicroService.users.register.RegistrationRequest;



public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	Optional<User> findByEmail(String email);
	


	
}
