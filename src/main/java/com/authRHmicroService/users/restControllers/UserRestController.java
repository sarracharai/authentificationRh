package com.authRHmicroService.users.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.authRHmicroService.users.entities.User;
import com.authRHmicroService.users.register.RegistrationRequest;
import com.authRHmicroService.users.repos.UserRepository;
import com.authRHmicroService.users.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired 
	UserRepository userRep;
	
	
	@RequestMapping(path = "all", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}
	
	@PostMapping("/register")
	public User register(@RequestBody RegistrationRequest request) {
		return userService.resgisterUser(request);
	}
	
	@GetMapping("/verifyEmail/{token}")
	public User verifyEmail(@PathVariable("token") String token){
		return userService.validateToken(token);
	}
}

