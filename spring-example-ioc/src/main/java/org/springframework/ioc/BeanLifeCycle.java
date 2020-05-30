package org.springframework.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.ioc.annotation.BeanScan;
import org.springframework.ioc.domain.ProxyTestInterface;
import org.springframework.ioc.domain.User;


@BeanScan(packages = "org.springframework.ioc.domain")
public class BeanLifeCycle {


	@Bean
	public User user() {
		return new User("yh", 199L);
	}


	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanLifeCycle.class);

		User us = context.getBean(User.class);
		System.out.println(us.toString());

		ProxyTestInterface proxyTestInterface = context.getBean(ProxyTestInterface.class);
		proxyTestInterface.test();


	}
}
