package com.vang.trash_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TrashServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrashServiceApplication.class, args);
	}

}