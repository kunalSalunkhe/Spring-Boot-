package com.first.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.bank.Dao")
@ComponentScan("com.bank.controller")
@ComponentScan("com.bank.repo")
@SpringBootApplication
public class MyBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBankApplication.class, args);
		
	}

}
