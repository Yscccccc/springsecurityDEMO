package com.ysc.service.impl;

import org.springframework.stereotype.Service;

import com.ysc.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService{

	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello" + name;
	}

}
