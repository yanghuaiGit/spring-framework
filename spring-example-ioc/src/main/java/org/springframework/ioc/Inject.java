package org.springframework.ioc;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.ioc.domain.SuperUser;
import org.springframework.ioc.domain.User;

public class Inject {

	public static void main(String[] args) {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(bf);
		bf.addBeanPostProcessor(bpp);
		RootBeanDefinition bd1 = new RootBeanDefinition(User.class);

		bd1.setScope(RootBeanDefinition.SCOPE_SINGLETON);
		bf.registerBeanDefinition("uss", bd1);

		RootBeanDefinition bd2 = new RootBeanDefinition(SuperUser.class);

		bd2.setScope(RootBeanDefinition.SCOPE_SINGLETON);
		bf.registerBeanDefinition("tss", bd2);


		User uss = (User) bf.getBean("uss");
		SuperUser tss = (SuperUser) bf.getBean("tss");

		System.out.println(uss);
		System.out.println(uss);
	}
}
