package org.hani.feedfood.security.services;

import java.util.ArrayList;

import org.hani.feedfood.security.models.JwtResponse;
import org.hani.feedfood.security.models.SignUpUser;
import org.hani.feedfood.security.models.User;
import org.hani.feedfood.security.util.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sun.media.sound.InvalidDataException;

@Service
public class SecurityService implements ISecurityService {

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private IValidationService validationService;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
//	@Autowired
//	private JwtUserDetailsService userDetailsService;

	SecurityService(BCryptPasswordEncoder bCryptPasswordEncoder, IValidationService validationService) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.validationService = validationService;
	}

	public JwtResponse signUp(SignUpUser user) throws InvalidDataException {

		if (validationService.validateSignUpData(user)) {
			String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
//	        applicationUserRepository.save(user);
//			try {
//			authenticate(user.getEmail(), user.getPassword());
//			}
//			catch(Exception e) {
//				
//			}
//			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//			final UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//					new ArrayList<>());
//			final String token = jwtTokenUtil.generateToken(userDetails);
			final String token = jwtTokenUtil.generateToken(user);
			return new JwtResponse(token);
		} else {
			throw new InvalidDataException("Invalid Request");
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(username, password);
			authenticationManager.authenticate(token);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@Override
	public JwtResponse login(User user) throws Exception {
	try {
		authenticate(user.getEmail(), user.getPassword());
		final String token = jwtTokenUtil.generateToken(user);
		return new JwtResponse(token);
	}
	catch (Exception e) {
		throw e;
	}
	}

}
