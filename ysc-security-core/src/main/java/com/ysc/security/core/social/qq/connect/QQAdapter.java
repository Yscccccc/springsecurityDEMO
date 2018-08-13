package com.ysc.security.core.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.ysc.security.core.social.qq.api.QQ;
import com.ysc.security.core.social.qq.api.QQUserInfo;

public class QQAdapter implements ApiAdapter<QQ>{

	@Override
	public boolean test(QQ api) {

		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getQQUserInfo();
		
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		values.setProfileUrl(null);
		values.setProviderUserId(userInfo.getOpenId());
		
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		// DO nothing
		
	}

}
