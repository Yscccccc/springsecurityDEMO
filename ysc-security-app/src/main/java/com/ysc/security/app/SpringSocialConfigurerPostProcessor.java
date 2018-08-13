package com.ysc.security.app;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.ysc.security.core.social.YscSpringSocialConfigurer;

@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor{
		
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (StringUtils.equals(beanName, "yscSocialSecurityConfig")) {
			YscSpringSocialConfigurer configurer = (YscSpringSocialConfigurer) bean;
			configurer.signupUrl("/social/signUp");
			return configurer;
		}
		return bean;
	}

}
