package com.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		System.out.println("Start111");
		SpringApplication.run(SchoolApplication.class, args);
	}

}
