package org.hani.feedfood.security.services;

import org.hani.feedfood.security.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sun.media.sound.InvalidDataException;

@Service
public class SecurityService implements ISecurityService{
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private IValidationService validationService;
	
	SecurityService(BCryptPasswordEncoder bCryptPasswordEncoder, IValidationService validationService){
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.validationService = validationService;
	}

	public User signUp(User user) throws InvalidDataException {
		
		if(validationService.validateSignUpData(user)) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return user;
//	        applicationUserRepository.save(user);
	}
	else {
		throw new InvalidDataException("Invalid Request");
	}
	}
}
