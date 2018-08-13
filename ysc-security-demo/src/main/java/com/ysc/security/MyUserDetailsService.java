package com.ysc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("表单登录用户名： " + username);
		//根据用户名查找用户信息
		//根据查找到的用户信息判断用户是否冻结
		return buildUser(username);
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		//根据用户名查找用户信息
		//根据查找到的用户信息判断用户是否冻结
		logger.info("社交登录用户Id： " + userId);
		return buildUser(userId);
	}
	
	private SocialUserDetails buildUser(String useId) {
		String password = passwordEncoder.encode("123456");
		logger.info("数据库的密码是： " + password);
		//返回的信息从用户的数据库中查出，这里只是做演示
		return new SocialUser(useId, password, 
				true, true, true, true,
//				AuthorityUtils.commaSeparatedStringToAuthorityList("xxx"));
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
	}
}
