package com.smart.context;

import org.apache.commons.logging.impl.ServletContextCleaner;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.smart.Car;


public class AnnotationApplicationContext {

	 public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);//启动Spring IOC容器
		Car car =ctx.getBean("car",Car.class);//注入bean

	}
}
