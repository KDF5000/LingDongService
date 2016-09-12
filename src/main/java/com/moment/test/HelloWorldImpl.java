package com.moment.test;

import javax.jws.WebService;

@WebService(serviceName="HelloWorldImpl") 
public class HelloWorldImpl implements HelloWorld{

	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return "hello world ,  " + name;
	}
}
