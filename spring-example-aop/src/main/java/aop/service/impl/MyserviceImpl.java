package aop.service.impl;

import aop.service.MyService;

public class MyserviceImpl implements MyService {
	@Override
	public void myMethod() {
		System.out.println("----myMethod-------");
	}
}
