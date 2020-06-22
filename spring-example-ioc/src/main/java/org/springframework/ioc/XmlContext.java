package org.springframework.ioc;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ioc.domain.User;


public class XmlContext {


	/**
	 * 关于spring容器管理bean的过程以及加载模式
	 * 1 需要将bean的定义信息申明在spring的配置文件中
	 * 2 需要通过spring抽献出的各种resource来指定对应的配置文件
	 * 3 需要显示申明一个spring 工厂 该工厂用来掌控我们在配置文件中所申明的各种bean以及bean之间的依赖关系与注入关系
	 * 4 需要顶一个配置信息读取器 读取器用来读取之前锁定义的bean配置文件信息
	 * 5 读取器的作用就是读取我们所申明的配置文件信息，并且将读取后的信息装配到之前所申明的工厂之中
	 * 6 需要将读取器与工厂以及资源对象进行相应的关联处理 资源期读取资源 加载到工厂之中
	 * 7 工厂所管理的全部对象装配完毕 可以供客户端直接调用 获取客户端想要使用的各种bean对象
	 * <p>
	 * <p>
	 * <p>
	 * Spring对于bean管理的核心组件:
	 * 1 资源抽象
	 * 2 工厂
	 * 3 配置信息读取器 桥梁作用 读取信息 转配到工厂
	 * 装配到工厂之后  只会和工厂进行交互 资源抽象 配置信息读取器的生命周期其实就是结束了
	 * <p>
	 * <p>
	 * <p>
	 * Spring bean实例的注册流程
	 * 定义好spring的配置文件
	 * 通过resource对象将spring配置文件进行抽象，抽象成一个具体的resource对象 如classpathResource
	 * 定义好将要使用的bean工厂 各种beanfactory
	 * 定义好xmlbeandefinitionreader对象 并将工厂对象作为参数传入进去，从而定义好两者的关联关系
	 * xmlbeandefinitionreader对象读取之前定义好的resourse对象
	 * 开始进行解析
	 * 针对xml文件进行各种元素以及元素属性的解析，这里面真正的解析是通过beandefinitionParderDelegate对象来完成的(委托模式)
	 * 通过beandefinitionParderDelegate 解析xml时 使用到了 模板模式
	 * 当所有的bean标签元素解析完毕之后，开始定义一个beandefinition对象 该对象是一个非常重要的对象，容纳了bean的所有属性
	 * beandefinition对象创建完毕之后，又创建一个beandefinitionHolder对象来持有这个beandefinition对象
	 * beandefinitionHolder 主要包含 beanname 以及 beandefinition
	 * registry进行注册 beandefinitionHolder
	 * 调用bean解析完毕的触发动作，从而触发相应的listener的动作
	 * <p>
	 * <p>
	 * <p>
	 * SpringBean的创建流程
	 * Spring所管理的Bean实际上是缓存在一个concurrentHahmap中 singletonObjects
	 * key是bean 的name(id) value是object
	 * 在创建bean之前，首先需要将该bean的创建标识指定好，标明已经或即将创建，目的是增加缓存效率
	 * 根据bean的scope属性，来确定当前bean是一个singleton还是propertype的bean
	 * 这两者创建过程是一致的，不同的是singleton的创建完之后有一个finally操作放入缓存中
	 * 通过java反射机制来创建bean的实例 在创建之前需要检查构造方法的访问修饰符，如果不是public 则会调用setaccessible(true)
	 * 当对象创建完毕之后，开始进行对象属性的注入
	 * 在对象属性注入的过程中 spring除去使用之前的beandefinition获取bean的相关信息外，还会通过反射获取到获取到bean中的真实属性信息
	 * 完成bean属性的注入或者抛出异常(propertyMathes)
	 * 如果bean是一个单利的，那么将所创建的bean添加到缓存中
	 */
	public static void main(String[] args) {

		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("META-INF/bean-constructor-dependency-injection.xml");
		User user1 = classPathXmlApplicationContext.getBean("user", User.class);
		System.out.println(user1);


//
//		Resource resource = new ClassPathResource("META-INF/bean-constructor-dependency-injection.xml");
//
//		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
//
//		//org.springframework.context.support.AbstractXmlApplicationContext 加载beandefinition就是用的这个
//		BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
////		String[] locations = { "META-INF/bean-constructor-dependency-injection.xml"};
////		beanDefinitionReader.loadBeanDefinitions(locations);
//		beanDefinitionReader.loadBeanDefinitions(resource);
//
//		User user = (User) defaultListableBeanFactory.getBean("user");
//
//		System.out.println(user);



	}
}
