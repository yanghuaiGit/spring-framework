package aop;

import aop.advisor.MyAdvisor;
import aop.service.MyService;
import aop.service.impl.MyserviceImpl;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


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
