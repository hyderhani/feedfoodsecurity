package org.hani.feedfood.security.services;

import org.hani.feedfood.security.models.SignUpUser;

public interface IValidationService {
	boolean validateSignUpData(SignUpUser user);
}
