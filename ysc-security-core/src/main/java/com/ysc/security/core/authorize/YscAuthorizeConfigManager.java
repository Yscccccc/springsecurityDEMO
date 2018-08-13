package com.ysc.security.core.authorize;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class YscAuthorizeConfigManager implements AuthorizeConfigManager {
	
	@Autowired
	private Set<AuthorizeConfigProvider> authorizeConfigProviders;
	
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		// TODO Auto-generated method stub
		for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
			authorizeConfigProvider.config(config);
		}
		
		config.anyRequest().authenticated();
	}

}
