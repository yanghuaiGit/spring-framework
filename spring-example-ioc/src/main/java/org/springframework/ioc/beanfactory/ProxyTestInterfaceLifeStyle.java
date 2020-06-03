package org.springframework.ioc.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.ioc.domain.ProxyTestInterface;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


@Component
public class ProxyTestInterfaceLifeStyle implements BeanFactoryPostProcessor, BeanPostProcessor, BeanClassLoaderAware, ProxyTestInterface {


	private ClassLoader classLoader;



	public ProxyTestInterfaceLifeStyle() {
	}



	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	System.out.println("-------postProcessBeanFactory-----");
	//这个是用于扩展BeanFactory 或者通过 beanFactory进行依赖查找和依赖注入
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
		if (bean instanceof ProxyTestInterface) {
			System.out.println("invoke before initialization");
		}
		return bean;
	}

	@Override
	@SuppressWarnings(value = {"rawtypes", "unchecked"})
	public Object postProcessAfterInitialization(final Object bean, String name) throws BeansException {
		if (bean instanceof ProxyTestInterface) {
			System.out.println("invoke after initialization");
			ProxyTestInterface newProxy = (ProxyTestInterface) Proxy.newProxyInstance(classLoader, new Class[]{ProxyTestInterface.class}, new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					System.out.println("before invoke");
					Object result = method.invoke(bean, args);
					if (method.getName().equals("hello")) {
						result = result.toString() + " from proxy";
					}
					System.out.println("after invoke");
					return result;
				}
			});
			return newProxy;
		}
		return bean;
	}

	@Override
	public void test() {
		System.out.println("test");
	}
}
