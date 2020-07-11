package org.hani.feedfood.security.controllers;

import org.hani.feedfood.security.models.User;
import org.hani.feedfood.security.services.ISecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.media.sound.InvalidDataException;

@RestController
@RequestMapping("/security")
public class SecurityController {

	private ISecurityService securityService;

	public SecurityController(
			ISecurityService securityService
			) {
		this.securityService=securityService;
	}

	@PostMapping("/sign-up")
	public ResponseEntity signUp(@RequestBody User user) {
		try
		{
		User response = securityService.signUp(user);
		return new ResponseEntity<User>(response, HttpStatus.OK);
		}
		catch (InvalidDataException e) {
			return new ResponseEntity<String>("Invalid Data", HttpStatus.BAD_REQUEST);
		}
	}
	
}
