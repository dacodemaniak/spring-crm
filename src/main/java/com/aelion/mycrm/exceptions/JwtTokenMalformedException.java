package com.aelion.mycrm.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {
	private static final long serialVersionUID = 1L;
	
	public JwtTokenMalformedException(String error) {
		super(error);
	}
}
