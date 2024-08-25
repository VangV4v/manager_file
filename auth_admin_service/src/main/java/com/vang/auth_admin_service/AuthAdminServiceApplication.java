package com.vang.auth_admin_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthAdminServiceApplication.class, args);
	}

}