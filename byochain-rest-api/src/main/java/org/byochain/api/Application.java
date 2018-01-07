package org.byochain.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Starter Class
 * @author Giuseppe Vincenzi
 *
 */
@SpringBootApplication(scanBasePackages={"org.byochain.services","org.byochain.api","org.byochain.model"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
