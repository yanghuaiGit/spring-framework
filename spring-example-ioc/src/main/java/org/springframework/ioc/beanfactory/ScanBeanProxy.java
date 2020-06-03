package org.springframework.ioc.beanfactory;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ScanBeanProxy<T> implements InvocationHandler, Serializable {
	private static final long serialVersionUID = -6424540398559729838L;


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("hello i am proxy");
		return "----proxy-----";
	}
}
