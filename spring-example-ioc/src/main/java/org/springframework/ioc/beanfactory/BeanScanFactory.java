package org.springframework.ioc.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Proxy;


public class BeanScanFactory implements FactoryBean<Object>, ApplicationContextAware {

	private Class<?> type;
	private ApplicationContext applicationContext;


	public BeanScanFactory() {
	}


	@Override
	public Object getObject() throws Exception {
		return getTarget();
	}

	@SuppressWarnings(value = {"rawtypes", "unchecked"})
	<T> T getTarget() {
		return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[]{type}, new ScanBeanProxy());

	}


	@Override
	public Class<?> getObjectType() {
		return this.type;
	}


	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
