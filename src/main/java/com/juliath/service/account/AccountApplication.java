package com.juliath.service.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

	@GetMapping("/")
	public List<String> Hello(){
		return List.of("Hello ","Julia");
	}

	@GetMapping("/me")
	public String HellotoME(){
		return "Welcome to Julia T.H";
	}


}
