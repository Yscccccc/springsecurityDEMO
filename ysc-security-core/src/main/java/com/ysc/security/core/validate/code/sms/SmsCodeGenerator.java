package com.ysc.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.ysc.security.core.properties.SecurityProperties;
import com.ysc.security.core.validate.code.ValidateCode;
import com.ysc.security.core.validate.code.ValidateCodeGenerator;

@Component("smsValidateCodeGenerator")
//@Component
public class SmsCodeGenerator implements ValidateCodeGenerator{
	
	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public ValidateCode generate(ServletWebRequest servletWebRequest) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	
	
}
