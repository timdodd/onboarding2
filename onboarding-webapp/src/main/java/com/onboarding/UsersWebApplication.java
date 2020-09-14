package com.onboarding;

import com.onboarding.controller.UserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsersWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersWebApplication.class, args);
	}

	@Bean
    @ConfigurationProperties(prefix = "app.user")
	public UserClient userClient() {
		return new UserClient();
	}
}
