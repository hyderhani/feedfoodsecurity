package org.hani.feedfood.security.models;

public class JwtResponse {

	String Token;
	

	public JwtResponse(String token) {
		super();
		Token = token;
	}

	 public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

}
