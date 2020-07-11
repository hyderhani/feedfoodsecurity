package org.hani.feedfood.security.services;

import org.hani.feedfood.security.models.User;

public interface IValidationService {
	boolean validateSignUpData(User user);
}
