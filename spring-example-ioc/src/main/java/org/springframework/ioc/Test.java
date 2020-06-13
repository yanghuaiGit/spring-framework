package org.springframework.ioc;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {

//		int scan = new ClassPathBeanDefinitionScanner(new DefaultListableBeanFactory()).scan("org.springframework.ioc.lifecycle.metadataconfiguration");
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
				ClassUtils.convertClassNameToResourcePath(new StandardEnvironment().resolveRequiredPlaceholders("org.springframework.ioc.lifecycle.metadataconfiguration"))+ '/' +"**/*.class";

		Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
		String a = "abc";
		String b = "def";
		System.out.println("abcdef" == (a + b));//false 因为stringbuilder


		String c = "abc";
		String d = "def";
		System.out.println("abcdef" == (c + d)); //true 编译器ast 抽象语法树 如果在一个方法里 2个final 常量 相加 解析到 会直接放入常量池里
		System.out.println((c + d));

		String e = "abc";
		String f = "def";
		String g = e+f;
		String h  = g.intern();

		System.out.println(g == h); //true


	}

}
