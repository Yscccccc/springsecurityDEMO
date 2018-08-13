package com.ysc.security.app.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.ysc.security.core.social.SocialAuthenticationFilterPostProcessor;

@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor{
	
	@Autowired
	private AuthenticationSuccessHandler yscAuthenticationSuccessHandler;
	
	@Override
	public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
		socialAuthenticationFilter.setAuthenticationSuccessHandler(yscAuthenticationSuccessHandler);
		
	}

}
