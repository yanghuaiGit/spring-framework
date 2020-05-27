package org.springframework.example.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class Ioc1 {

	@Bean
	public Integer int1 (){
	return  new Integer(12);
	}


	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Ioc1.class);
		Integer int1 = (Integer)annotationConfigApplicationContext.getBean("int1");
		System.out.println("----"+int1);
	}
}
