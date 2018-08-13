package com.ysc.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.ysc.security.core.properties.SecurityConstants;
import com.ysc.security.core.properties.SecurityProperties;

@Component
public class YscAuthorizeConfigProvider implements AuthorizeConfigProvider{
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		// TODO Auto-generated method stub
		config.antMatchers(
				SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
				SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
				securityProperties.getBrowser().getLoginPage(),
				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
				securityProperties.getBrowser().getSignUpUrl(),
				securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
				securityProperties.getBrowser().getSignOutUrl())
			.permitAll();
		
	}

}
