package com.example.config_cloud_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigCloudServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigCloudServerApplication.class, args);
	}

}
