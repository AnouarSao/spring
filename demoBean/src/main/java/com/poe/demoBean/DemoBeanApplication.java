package com.poe.demoBean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:beans.xml")

@SpringBootApplication
public class DemoBeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBeanApplication.class, args);
	}

}
