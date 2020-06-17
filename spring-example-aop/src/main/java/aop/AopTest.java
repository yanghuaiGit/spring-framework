package aop;

import aop.advisor.MyAdvisor;
import aop.service.MyService;
import aop.service.impl.MyserviceImpl;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过 proxyFactoryBean 配置相应的代理对象的信息
 * 在获取 proxyFactoryBean 实例时 本质上获取到的是 proxyFactoryBean#getObject返回的对象实例
 * 在整个proxyFactoryBean 实例的构建与缓存的过程中 其流程和普通的bean对象是一致的
 * 当创建的 proxyFactoryBean 对象后 spring会创建当前对象是否是一个factoryBean实例 是的话 就会调用getObject 返回这个对象
 * 如果是 spring会进入到一个新的流程之中
 * proxyFactoryBean 根据配置的信息 进行动态代理 使用cglib jdk等方式进行代理
 */

public class AopTest {

	@Bean
	public MyService myService(){
		return new MyserviceImpl();
	}

	@Bean
	public MethodInterceptor methodInterceptor(){
		return new MyAdvisor();
	}

	@Bean
	public ProxyFactoryBean proxyFactoryBean(){
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(myService());
		proxyFactoryBean.setInterfaces(MyService.class);
		proxyFactoryBean.setInterceptorNames("methodInterceptor");
		return proxyFactoryBean;
	}


	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopTest.class);

		MyService myService = (MyService)context.getBean("proxyFactoryBean");
		myService.myMethod();

		System.out.println(myService.getClass());
		System.out.println(myService.getClass().getSuperclass());
		System.out.println(myService.getClass().getInterfaces());
	}
}
