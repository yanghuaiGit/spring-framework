/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.ioc.lifecycle.metadataconfiguration;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.ioc.domain.User;

/**
 * Bean 元信息配置示例
 * 来源于 properties文件
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class BeanMetadataConfigurationDemo {


	/**
	 *  // 创建 BeanFactory 容器
	 *         DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
	 *         XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
	 *         // XML 配置文件 ClassPath 路径
	 *         String location = "classpath:/META-INF/dependency-lookup-context.xml";
	 *         // 加载配置
	 *         int beanDefinitionsCount = reader.loadBeanDefinitions(location);
	 *         System.out.println("Bean 定义加载的数量：" + beanDefinitionsCount);
	 * @param args
	 */

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		// 实例化基于 Properties 资源 BeanDefinitionReader
		PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
		String location = "META-INF/user.properties";
		// 基于 ClassPath 加载 properties 资源
		Resource resource = new ClassPathResource(location);
		// 指定字符编码 UTF-8
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
		int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);

		//这个方法会导致乱码
		//int i = beanDefinitionReader.loadBeanDefinitions(location);
		System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);
		// 通过 Bean Id 和类型进行依赖查找
		User user = beanFactory.getBean("user", User.class);
		System.out.println(user);

	}

}
