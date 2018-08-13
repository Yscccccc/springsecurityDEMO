package com.ysc.security.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

public class YscExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy{
	public YscExpiredSessionStrategy(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}
	
//	@Override
//	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
//		event.getResponse().setContentType("application/json;charset=UTF-8");
//		event.getResponse().getWriter().write("并发登录！");
//		
//	}
	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		onSessionInvalid(event.getRequest(), event.getResponse());
	}
	
	@Override
	protected boolean isConcurrency() {
		return true;
	}
}
