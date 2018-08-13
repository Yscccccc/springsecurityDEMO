package com.ysc.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ysc.security.core.properties.SecurityProperties;
import com.ysc.security.core.validate.code.image.ImageCodeGenerator;
import com.ysc.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.ysc.security.core.validate.code.sms.SmsCodeSender;

@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	//@ConditionalOnMissingBean中的name不是讲beanid设为该值
	//@Bean没有设置name属性是，beanId为方法名
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}
}
