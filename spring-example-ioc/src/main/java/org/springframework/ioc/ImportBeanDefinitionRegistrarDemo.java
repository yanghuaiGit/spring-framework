package org.springframework.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.ioc.annotation.BeanScan;
import org.springframework.ioc.domain.ProxyTestInterface;
import org.springframework.ioc.domain.User;

@BeanScan(packages = "org.springframework.ioc.domain")
public class ImportBeanDefinitionRegistrarDemo {

	@Bean
	public User user() {
		return User.createUser();
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportBeanDefinitionRegistrarDemo.class);

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.springframework.ioc");
		ProxyTestInterface proxyTestInterface = context.getBean(ProxyTestInterface.class);
		proxyTestInterface.test();
		User bean = context.getBean(User.class);
		System.out.println(bean);

	}
}
