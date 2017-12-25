package org.byochain.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"org.byochain.services","org.byochain.api","org.byochain.model"})
//@ComponentScan(basePackages = {"org.byochain.services","org.byochain.api","org.byochain.model"})
//@Import(ModelConfig.class)
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
