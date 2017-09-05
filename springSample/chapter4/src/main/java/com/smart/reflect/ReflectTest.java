package com.smart.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//add by CUI for test JAVA reflect mechanism
public class ReflectTest {
    public static Car initByDefaultConst() throws  Throwable {
        //流程：获取类装载器->获取类->获取默认构造器->实例化类->获取方法指针->调用方法设置参数(如是纯类操作可直接赋值了)
        //(1) 通过类型装载器获取Car类对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.smart.reflect.Car");

        //(2) 获取类的默认构造器对象，并实例化car
        Constructor cons = clazz.getDeclaredConstructor((Class[])null); //传入参数为null
        Car car = (Car)cons.newInstance();

        //(3) 通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car, "红旗A380");

        Field field[] = clazz.getDeclaredFields();

        return car;
    }

    public static void main(String[] args) throws  Throwable {
        Car car = initByDefaultConst();
        car.introduce();
    }
}
