package com.axur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WhitelistApplication {
	
	public static final String APP_LOG = "[AXUR_ANALYZER]";

	public static void main(String[] args) {
		SpringApplication.run(WhitelistApplication.class, args);
	}

}
