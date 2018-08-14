package com.jincou.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages="com.jincou.websocket")
@EnableScheduling
@SpringBootApplication
public class WebsocketstudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketstudyApplication.class, args);
	}
}
