package com.mybestcoding.hmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmtApplication.class, args);
	}

}
