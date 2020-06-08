package com.example.adoption;

import com.example.adoption.web.mail.MailConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties(MailConfig.class)
public class AdoptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptionApplication.class, args);

	}

}
