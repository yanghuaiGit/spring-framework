package org.springframework.ioc;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ioc.annotation.BeanScan;
import org.springframework.ioc.domain.ProxyTestInterface;
import org.springframework.ioc.domain.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

//@BeanScan(packages = "org.springframework.ioc.domain")
@EnableTransactionManagement
//@EnableCaching
@Configuration
public class ImportBeanDefinitionRegistrarDemo {

	@Bean
	public PlatformTransactionManager platformTransactionManager(){
		return new PlatformTransactionManager() {
			@Override
			public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
				return null;
			}

			@Override
			public void commit(TransactionStatus status) throws TransactionException {

			}

			@Override
			public void rollback(TransactionStatus status) throws TransactionException {

			}
		};
	}
	@Bean
	@Transactional
	public User user() {
		return User.createUser();
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportBeanDefinitionRegistrarDemo.class);

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.springframework.ioc");
//		ProxyTestInterface proxyTestInterface = context.getBean(ProxyTestInterface.class);
//		proxyTestInterface.test();
		User bean = context.getBean(User.class);
		System.out.println(bean);

	}
}
