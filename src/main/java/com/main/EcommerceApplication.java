package com.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"com.main.service","com.main.config","com.main.repository","com.main.controller","com.main.exception","com.main.model","com.main.response","com.main.request"})
@EnableJpaRepositories(basePackages="com.main.Repository")
@EntityScan(basePackages="com.main,model")
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
