package com.gamebacket.vercel.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GamebacketApplication {
	public static void main(String[] args) {
		SpringApplication.run(GamebacketApplication.class, args);
	}

}
