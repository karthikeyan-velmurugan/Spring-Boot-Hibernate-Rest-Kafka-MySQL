package com.cyber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {JpaRepositoriesAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
public class CyberSecurityWorksTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(CyberSecurityWorksTaskApplication.class, args);
	}
}
