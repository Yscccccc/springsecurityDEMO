package com.ysc.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8924081808082266701L;

	public ValidateCodeException(String msg) {
		super(msg);
		
	}
	
}
