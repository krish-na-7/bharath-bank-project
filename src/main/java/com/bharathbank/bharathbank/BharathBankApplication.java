package com.bharathbank.bharathbank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class BharathBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BharathBankApplication.class, args);

	}

}
