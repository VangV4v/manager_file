package com.vang.forgot_password_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ForgotPasswordServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForgotPasswordServiceApplication.class, args);
	}

}