package org.hani.feedfood.security.controllers;

import org.hani.feedfood.security.models.JwtResponse;
import org.hani.feedfood.security.models.SignUpUser;
import org.hani.feedfood.security.models.User;
import org.hani.feedfood.security.services.ISecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
	public ResponseEntity<JwtResponse> signUp(@RequestBody SignUpUser user) {
		try
		{
		JwtResponse response = securityService.signUp(user);
		return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
		}
		catch (InvalidDataException exc) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Invalid Data");
		}
	}
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody User user) {
		try
		{
		JwtResponse response = securityService.login(user);
		return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
		}
		catch (BadCredentialsException exc) {
			throw new ResponseStatusException(
			           HttpStatus.UNAUTHORIZED, exc.getMessage());
		}
		catch (DisabledException exc) {
			throw new ResponseStatusException(
			           HttpStatus.FORBIDDEN, exc.getMessage());
		}
		catch (Exception exc) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, exc.getMessage());
		}
	}
	@PostMapping("/logout")
	public ResponseEntity<JwtResponse> logout() {
		throw new ResponseStatusException(
		           HttpStatus.NOT_IMPLEMENTED, "Not Implemented");
	}
}