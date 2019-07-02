package com.joeunins.infraboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.joeunins.infraboard")
public class InfraboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfraboardApplication.class, args);
	}

}

