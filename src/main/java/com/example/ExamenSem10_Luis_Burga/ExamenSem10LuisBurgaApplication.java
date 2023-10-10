package com.example.ExamenSem10_Luis_Burga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class ExamenSem10LuisBurgaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ExamenSem10LuisBurgaApplication.class, args);
	}

}
