package com.ysc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
	
	ValidateCode generate(ServletWebRequest servletWebRequest);
}
