package com.cognizant.truyum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.cognizant.truyum.dao.ConnectionHandler;

@SpringBootApplication
@ComponentScan("com.*")
public class TruyumApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruyumApplication.class, args);
		System.out.println("********************************************"+ConnectionHandler.getConnection());
	}

}
