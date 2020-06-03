package org.springframework.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ioc.annotation.BeanScan;
import org.springframework.ioc.domain.ProxyTestInterface;

@BeanScan(packages = "org.springframework.ioc.domain")
public class ImportBeanDefinitionRegistrarDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportBeanDefinitionRegistrarDemo.class);

		ProxyTestInterface proxyTestInterface = context.getBean(ProxyTestInterface.class);
		proxyTestInterface.test();

	}
}
