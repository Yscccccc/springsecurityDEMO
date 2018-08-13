package com.ysc.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.ysc.security.core.validate.code.ValidateCodeGenerator;
import com.ysc.security.core.validate.code.image.ImageCode;

//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator{

	@Override
	public ImageCode generate(ServletWebRequest servletWebRequest) {
		System.out.println("更高级的图形验证码生成代码");
		return null;
	}

}
