package org.hani.feedfood.security.services;

import org.hani.feedfood.security.models.User;
import org.springframework.stereotype.Service;

@Service
public class ValidationService implements IValidationService {

	@Override
	public boolean validateSignUpData(User user) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
