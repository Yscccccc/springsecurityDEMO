package com.ysc.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.ysc.security.core.validate.code.ValidateCode;

public class ImageCode extends ValidateCode{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 829172788395810255L;
	private BufferedImage image;
	
	public ImageCode(BufferedImage image, String code, int expireIn) {
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
