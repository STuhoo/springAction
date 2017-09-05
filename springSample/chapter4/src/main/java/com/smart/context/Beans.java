package com.smart.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smart.Car;

@Configuration
public class Beans {

	@Bean(name = "car") //此处会作为bean的方法被调用
	public Car buildCar() {
		Car car = new Car();
		car.setBrand("buildCar:红旗CA70");
		car.setMaxSpeed(200);
		return car;
	}
}
