package com.authRHmicroService.users.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.authRHmicroService.users.entities.Role;
import com.authRHmicroService.users.entities.User;
import com.authRHmicroService.users.exceptions.EmailAlreadyExistsException;
import com.authRHmicroService.users.exceptions.ExpiredTokenException;
import com.authRHmicroService.users.exceptions.InvalidTokenException;
import com.authRHmicroService.users.register.RegistrationRequest;
import com.authRHmicroService.users.register.VerificationToken;
import com.authRHmicroService.users.register.VerificationTokenRepository;
import com.authRHmicroService.users.repos.RoleRepository;
import com.authRHmicroService.users.repos.UserRepository;
import com.authRHmicroService.users.util.EmailSender;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRep;
	
	@Autowired
	RoleRepository roleRep;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	VerificationTokenRepository verificationTokenRepo;
	
	@Autowired
	EmailSender emailSender;
	
	

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRep.save(user);
	}

	@Override
	public User findUserByUsername(String username) {
		return userRep.findByUsername(username);
	}

	@Override
	public Role addRole(Role role) {
		return roleRep.save(role) ;
	}

	@Override
	public User addRoleToUser(String username, String rolename) {
		User usr = userRep.findByUsername(username);
		 Role r = roleRep.findByRole(rolename);
		 usr.getRoles().add(r);
		 //userRep.save(usr) // je l'ai mis dans un commentaire car on a utilisé @transactionnal donc il va savé automatiquement 
		 return usr;
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User resgisterUser(RegistrationRequest request) {
		Optional<User>  optionalUser = userRep.findByEmail(request.getEmail());
		if(optionalUser.isPresent())
			throw new EmailAlreadyExistsException("Email déjà existant!");

		User newUser = new User();
		newUser.setUsername(request.getUsername());
		newUser.setEmail(request.getEmail());

		newUser.setPassword( bCryptPasswordEncoder.encode( request.getPassword() )  );
		newUser.setEnabled(false);

		userRep.save(newUser);

		Role r = roleRep.findByRole("USER");
		List<Role> roles = new ArrayList<>();
		roles.add(r);
		newUser.setRoles(roles);

		//génére le code secret
		String code = this.generateCode();

		VerificationToken token = new VerificationToken(code, newUser);
		verificationTokenRepo.save(token);
		
		//envoyer le code par l'email à l'utilisateur
		sendEmailUser(newUser,token.getToken());

		return userRep.save(newUser);
	}

	
	
	private String generateCode() {
		Random random = new Random();
		Integer code = 100000 + random.nextInt(900000);

		return code.toString();

	}
	
	@Override
	public void sendEmailUser(User u, String code) {
		String emailBody ="Bonjour "+ "<h1>"+u.getUsername() +"</h1>" +
		" Votre code de validation est "+"<h1>"+code+"</h1>";
		emailSender.sendEmail(u.getEmail(), emailBody);
		}

	

	@Override
	public User validateToken(String code) {
		VerificationToken token = verificationTokenRepo.findByToken(code);

		if(token == null){
			throw new InvalidTokenException("Invalid Token !!!!!!!");
		}

		User user = token.getUser();

		Calendar calendar = Calendar.getInstance();

		if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
			verificationTokenRepo.delete(token);
			throw new ExpiredTokenException("expired Token");
		}

		user.setEnabled(true);
		userRep.save(user);
		return user;
	}
	
	

}
