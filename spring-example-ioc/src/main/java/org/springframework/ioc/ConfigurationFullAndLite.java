package org.springframework.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ioc.domain.SuperUser;
import org.springframework.ioc.domain.User;

//https://www.cnblogs.com/heqiyoujing/p/12995490.html full lite模式区别
@Configuration
//@Component
public class ConfigurationFullAndLite {

	@Bean
	public User userDao(){
		// 会被打印几次 ？？ @Component 2次   @Configuration 1次  因为这个就涉及到了@configuration的增强
		//@configuration 的bean 可以被看做一个容器,在调用其他@bean的方法的时候  其实是被代理了 类似从beanfactory里去获取
		System.out.println("注入UserDao");
		return User.createUser();
	}

	@Bean
	public SuperUser orderDao(){
		// 在这里调用userDao()方法
		userDao();
		return new SuperUser();
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationFullAndLite.class);
	}
}
