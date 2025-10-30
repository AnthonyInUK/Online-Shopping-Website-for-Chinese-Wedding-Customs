package com.luv2code.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Test version of Spring Boot application that excludes Okta
 */
@SpringBootApplication(exclude = {
    com.okta.spring.boot.oauth.OktaOAuth2AutoConfig.class
})
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}

