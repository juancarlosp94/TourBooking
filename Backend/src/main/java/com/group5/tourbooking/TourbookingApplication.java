package com.group5.tourbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.group5.tourbooking.controller", "com.group5.tourbooking.service"})
public class TourbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourbookingApplication.class, args);
	}
}
