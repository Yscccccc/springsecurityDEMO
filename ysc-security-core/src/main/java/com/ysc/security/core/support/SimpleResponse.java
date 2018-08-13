package com.ysc.security.core.support;

public class SimpleResponse {
	
	public SimpleResponse() {

	}
	
	public SimpleResponse(Object content) {
		super();
		this.content = content;
	}

	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	
}
