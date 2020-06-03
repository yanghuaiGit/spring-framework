package org.springframework.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ioc.annotation.BeanScan;
import org.springframework.ioc.domain.ProxyTestInterface;
import org.springframework.ioc.domain.User;

@Configuration
@BeanScan(packages = "org.springframework.ioc.domain")
public class BeanLifeCycle {


	@Bean(name = "User")
	public User user() {
		return  User.createUser();
	}


	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.springframework.ioc");

		ProxyTestInterface us = context.getBean(ProxyTestInterface.class);
		us.test();
	}
}
