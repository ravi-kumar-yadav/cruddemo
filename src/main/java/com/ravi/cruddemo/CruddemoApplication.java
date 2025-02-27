package com.ravi.cruddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);

		System.out.println("********************************");
		System.out.println("Started CrudDemo Successfully!!!");
		System.out.println("********************************");
	}

}
