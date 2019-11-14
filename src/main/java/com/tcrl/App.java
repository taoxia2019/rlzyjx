package com.tcrl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.tcrl.dao")
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启security注解
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
