package com.ysc.security.app.social.openid;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.UsersConnectionRepository;


public class OpenIdAuthenticationProvider implements AuthenticationProvider{
	
	private UserDetailsService userDetailsService;
	private UsersConnectionRepository usersConnectionRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		OpenIdAuthenticationToken authenticationToken = (OpenIdAuthenticationToken) authentication;
		
		Set<String> providerUserIds = new HashSet<>();
		providerUserIds.add((String) authenticationToken.getPrincipal());
		Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(authenticationToken.getProviderId(), providerUserIds);
		
		if (CollectionUtils.isEmpty(userIds) || userIds.size() != 1) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		
		String userId = userIds.iterator().next();
		
		UserDetails user = userDetailsService.loadUserByUsername(userId);
		
		if (user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		
		OpenIdAuthenticationToken authenticationResult = new OpenIdAuthenticationToken(user, user.getAuthorities());
		authenticationResult.setDetails(authenticationToken.getDetails());
		
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public UsersConnectionRepository getUsersConnectionRepository() {
		return usersConnectionRepository;
	}

	public void setUsersConnectionRepository(UsersConnectionRepository usersConnectionRepository) {
		this.usersConnectionRepository = usersConnectionRepository;
	}

}
