package org.hani.feedfood.security.services;

import org.hani.feedfood.security.models.JwtResponse;
import org.hani.feedfood.security.models.SignUpUser;
import org.hani.feedfood.security.models.User;

import com.sun.media.sound.InvalidDataException;

public interface ISecurityService {

	public JwtResponse signUp(SignUpUser user) throws InvalidDataException;

	public JwtResponse login(User user) throws Exception;
}
