package dev.flexteam.flexboost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class FlexboostApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(FlexboostApplication.class);
		app.run();


	}

}
