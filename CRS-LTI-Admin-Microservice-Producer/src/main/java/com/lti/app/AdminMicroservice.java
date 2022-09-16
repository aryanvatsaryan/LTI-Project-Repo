package com.lti.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
@AutoConfiguration
@ComponentScan({"com.lti.*"})
@EnableEurekaClient
public class AdminMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(AdminMicroservice.class, args);
	}

}
