package com.ysc.security.browser.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysc.security.core.support.SimpleResponse;

public class YscLogoutSuccessHandler implements LogoutSuccessHandler{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String signOutUrl;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public YscLogoutSuccessHandler(String signOutUrl) {
		this.signOutUrl = signOutUrl;
	}
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		logger.info("退出成功");
		
		if (StringUtils.isBlank(signOutUrl)) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
		} else {
			response.sendRedirect(signOutUrl);
		}
		
	}

}
