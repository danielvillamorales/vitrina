package com.kostazul.vitrina;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class VitrinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitrinaApplication.class, args);
	}

}
