package com.ysc.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import com.ysc.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.ysc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.ysc.security.core.authorize.AuthorizeConfigManager;
import com.ysc.security.core.properties.SecurityConstants;
import com.ysc.security.core.properties.SecurityProperties;
import com.ysc.security.core.validate.code.ValidateCodeSecurityConfig;

@Configuration
@EnableResourceServer
public class YscResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	protected AuthenticationSuccessHandler yscAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler yscAuthenticationFailureHandler;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer yscSocialSecurityConfig;
	
	@Autowired
	private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;
	
	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
		.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
		.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
		.successHandler(yscAuthenticationSuccessHandler)
		.failureHandler(yscAuthenticationFailureHandler);
		
		http.apply(validateCodeSecurityConfig)
				.and()
			.apply(smsCodeAuthenticationSecurityConfig)
				.and()
			.apply(yscSocialSecurityConfig)
				.and()
			.apply(openIdAuthenticationSecurityConfig)
				.and()
//			.authorizeRequests()
//				.antMatchers(
//					SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
//					SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
//					securityProperties.getBrowser().getLoginPage(),
//					SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
//					securityProperties.getBrowser().getSignUpUrl(),
//					securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
//					securityProperties.getBrowser().getSignOutUrl(),
//					"/user/regist", "/social/signUp")
//					.permitAll()
//				.anyRequest()
//				.authenticated()
//				.and()
			.csrf().disable();
		
		authorizeConfigManager.config(http.authorizeRequests());
	}
	
	
}
