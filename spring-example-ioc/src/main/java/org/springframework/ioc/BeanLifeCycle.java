package org.springframework.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ioc.annotation.BeanScan;
import org.springframework.ioc.domain.ProxyTestInterface;
import org.springframework.ioc.domain.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

@Configuration
//@BeanScan(packages = "org.springframework.ioc.domain")
public class BeanLifeCycle {


	@Bean(name = "User")
	@Primary
	@Transactional
	public User user() {
		return  User.createUser();
	}


	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.springframework.ioc");

		ProxyTestInterface us = context.getBean(ProxyTestInterface.class);
		us.test();
	}
}
