package com.authRHmicroService.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.authRHmicroService.users.entities.Role;
import com.authRHmicroService.users.entities.User;
import com.authRHmicroService.users.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AuthRhApplication {
	
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AuthRhApplication.class, args);
	}
	
	/*
	@PostConstruct
	void init_users() {
	
	//ajouter les rôles
	userService.addRole(new Role(null,"ADMIN"));
	userService.addRole(new Role(null,"USER"));
	userService.addRole(new Role(null,"RESPONSABLE"));
	
	userService.saveUser(new User(null, "admin", "1234567", true, null, null));
	userService.saveUser(new User(null, "responsable", "12334567", true, null, null));
	userService.saveUser(new User(null, "sadak", "1234567", true, null, null));
	userService.saveUser(new User(null, "sarra", "1234567", true, null, null));
	//ajouter les rôles aux users
	 
	userService.addRoleToUser("admin", "ADMIN");
	userService.addRoleToUser("responsable", "RESPONSABLE");
	userService.addRoleToUser("sadak", "USER");
	userService.addRoleToUser("sarra", "USER");
	}
*/
	
	
}
