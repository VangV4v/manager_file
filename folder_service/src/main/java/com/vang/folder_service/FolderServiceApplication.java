package com.vang.folder_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FolderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FolderServiceApplication.class, args);
	}

}