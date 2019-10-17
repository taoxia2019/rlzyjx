package com.tcrl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.tcrl.dao")
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启security注解
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
