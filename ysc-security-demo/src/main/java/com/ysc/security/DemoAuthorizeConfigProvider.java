package com.ysc.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.ysc.security.core.authorize.AuthorizeConfigProvider;

@Component
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider{

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		//配置页面访问权限
//		config.antMatchers("/demo.html").hasRole("ADMIN");
		config.antMatchers("/user").hasRole("ADMIN");
	}

}
