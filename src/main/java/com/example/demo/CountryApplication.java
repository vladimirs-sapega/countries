package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CountryApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CountryApplication.class, args);
	}
}