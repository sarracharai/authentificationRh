package com.authRHmicroService.users.service;

import java.util.List;

import com.authRHmicroService.users.entities.Role;
import com.authRHmicroService.users.entities.User;
import com.authRHmicroService.users.register.RegistrationRequest;

public interface UserService {
	User saveUser(User user);
	User findUserByUsername (String username);
	Role addRole(Role role);
	User addRoleToUser(String username, String rolename);
	List<User> findAllUsers();
	
	User resgisterUser(RegistrationRequest request);
	public void sendEmailUser(User u, String code);
	public User validateToken(String code);

}
