package aop.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 这是通知 增强方法
 */
public class MyAdvisor implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("---before MyAdvisor invoke");

		Object proceed = invocation.proceed();

		System.out.println("----after MyAdvisor invoke------");

		return proceed;
	}
}
