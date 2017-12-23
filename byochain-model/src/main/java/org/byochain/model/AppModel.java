package org.byochain.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "org.byochain.model" })
public class AppModel {
	public static void main(String[] args) {
		SpringApplication.run(AppModel.class, args);
	}
}
