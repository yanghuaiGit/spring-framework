package org.springframework.ioc.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.ioc.beanfactory.BeanRegister;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BeanRegister.class)
public @interface BeanScan {

	String packages() default "";

}
