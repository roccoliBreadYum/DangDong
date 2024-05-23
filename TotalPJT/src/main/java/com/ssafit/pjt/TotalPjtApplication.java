package com.ssafit.pjt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TotalPjtApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotalPjtApplication.class, args);
	}

}
