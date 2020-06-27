package org.springframework.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ioc.domain.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@BeanScan(packages = "org.springframework.ioc.domain")
@EnableTransactionManagement
//@EnableCaching
@Configuration
@ComponentScan //默认会扫描同包以及子包下的类
public class ImportBeanDefinitionRegistrarDemo {

//	@Autowired
//	private User user;

//	@Autowired
//	public ImportBeanDefinitionRegistrarDemo(User user){
//		this.user = user;
//	}

//	@Autowired
//	private ImportBeanDefinitionRegistrarDemo ImportBeanDefinitionRegistrarDemo;

//	public void setUser(User user) {
//		this.user = user;
//	}

	/**
	 * PlatformTransactionManager 由一个父类统一管理各个不同的事务
	 *
	 * @return
	 */
	@Bean
	public PlatformTransactionManager platformTransactionManager() {
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

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportBeanDefinitionRegistrarDemo.class);

		ImportBeanDefinitionRegistrarDemo importBeanDefinitionRegistrarDemo = context.getBean(ImportBeanDefinitionRegistrarDemo.class);

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.springframework.ioc");
//		ProxyTestInterface proxyTestInterface = context.getBean(ProxyTestInterface.class);
//		proxyTestInterface.test();
		User bean = context.getBean(User.class);
		System.out.println(bean);

	}
}
