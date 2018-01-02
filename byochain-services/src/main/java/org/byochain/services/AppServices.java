package org.byochain.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "org.byochain.services", "org.byochain.model" })
public class AppServices {
	public static void main(String[] args) {
		SpringApplication.run(AppServices.class, args);
	}
}
