package com.authRHmicroService.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.authRHmicroService.users.entities.Role;
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
	
	//ajouter les users
	userService.saveUser(new User(null,"admin","123",true,null));
	
	userService.saveUser(new User(null,"sadak","123",true,null));
	userService.saveUser(new User(null,"sarra","123",true,null));
	//ajouter les rôles aux users
	 
	userService.addRoleToUser("admin", "ADMIN");
	userService.addRoleToUser("admin", "USER");
	userService.addRoleToUser("sadak", "USER");
	userService.addRoleToUser("sarra", "USER");
	}
	*/
	
	
}
