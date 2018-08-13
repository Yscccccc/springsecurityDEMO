package com.ysc.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import com.ysc.security.browser.session.YscExpiredSessionStrategy;
import com.ysc.security.core.authentication.AbstractChannelSecurityConfig;
import com.ysc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.ysc.security.core.authorize.AuthorizeConfigManager;
import com.ysc.security.core.properties.SecurityConstants;
import com.ysc.security.core.properties.SecurityProperties;
import com.ysc.security.core.validate.code.ValidateCodeSecurityConfig;

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig{
	
	@Autowired
	private SecurityProperties securityProperties;
	
//	@Autowired
//	private AuthenticationSuccessHandler yscAuthenticationSuccessHandler;
//	
//	@Autowired
//	private AuthenticationFailureHandler yscAuthencticationFailureHandler;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer yscSocialSecurityConfig;
	
	@Autowired
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
	
	@Autowired
	private InvalidSessionStrategy invalidSessionStrategy;
	
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;
	
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//		validateCodeFilter.setAuthenticationFailureHandler(yscAuthencticationFailureHandler);
//		validateCodeFilter.setSecurityProperties(securityProperties);
//		validateCodeFilter.afterPropertiesSet();
//		
//		SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
//		smsCodeFilter.setAuthenticationFailureHandler(yscAuthencticationFailureHandler);
//		smsCodeFilter.setSecurityProperties(securityProperties);
//		smsCodeFilter.afterPropertiesSet();
		
		

//		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
//			.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
//			.formLogin()
//			.loginPage("/authentication/require")
//			.loginProcessingUrl("/authentication/form")
//			.successHandler(yscAuthenticationSuccessHandler)
//			.failureHandler(yscAuthencticationFailureHandler)
//			.and()
//			.rememberMe()
//			.tokenRepository(persistentTokenRepository())
//			.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
//			.userDetailsService(userDetailsService)
//			.and()
//			.authorizeRequests()
//			.antMatchers("/authentication/require", 
//					securityProperties.getBrowser().getLoginPage(), 
//					"/code/*").permitAll()
//			.anyRequest()
//			.authenticated()
//			.and()
//			.csrf().disable()
//			.apply(smsCodeAuthenticationSecurityConfig);
		
		
		applyPasswordAuthenticationConfig(http);
		
		http.apply(validateCodeSecurityConfig)
				.and()
			.apply(smsCodeAuthenticationSecurityConfig)
				.and()
			.apply(yscSocialSecurityConfig)
				.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
				.and()
			.sessionManagement()
				.invalidSessionStrategy(invalidSessionStrategy)
				.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
				.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
				.expiredSessionStrategy(sessionInformationExpiredStrategy)
				.and()
				.and()
			.logout()
				.logoutUrl("/signOut")
//				.logoutSuccessUrl("/ysc-logout.html")
				.logoutSuccessHandler(logoutSuccessHandler)
				.deleteCookies("JSESSIONID")
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
//					"/user/regist")
//					.permitAll()
//				//ps: 只有拥有ADMIN角色权限的人才能访问方法为get,/user/*的url
//				.antMatchers(HttpMethod.GET,"/user/*").hasRole("ADMIN")
//				//权限表达式连起来写(具体操作 在rbac权限管理)
//				//.antMatchers(HttpMethod.GET,"/user/*").access("hasRole('ADMIN') and hasIpAddress('xxx')")
//				.anyRequest()
//				.authenticated()
//				.and()
			.csrf().disable();
		
		authorizeConfigManager.config(http.authorizeRequests());
		
	}
	
	
}
