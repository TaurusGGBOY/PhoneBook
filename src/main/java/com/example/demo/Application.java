package com.example.demo;

import java.util.Arrays;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@MapperScan(basePackages = { "com.example.mapper" })
@ComponentScan(basePackages = { "com.example.mapper", "com.example.demo.controller", "com.example.service",
		"com.example.model" })

public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

	@Bean

	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {

		return args -> {

			// System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();

			Arrays.sort(beanNames);

			for (String beanName : beanNames) {

				// System.out.println(beanName);

			}

		};

	}

}