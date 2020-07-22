package org.hani.feedfood.security.services;

import org.hani.feedfood.security.models.SignUpUser;
import org.springframework.stereotype.Service;

@Service
public class ValidationService implements IValidationService {

	@Override
	public boolean validateSignUpData(SignUpUser user) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
