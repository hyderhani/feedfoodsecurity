package org.hani.feedfood.security.services;

import org.hani.feedfood.security.models.User;

import com.sun.media.sound.InvalidDataException;

public interface ISecurityService {

	public User signUp(User user) throws InvalidDataException;
}