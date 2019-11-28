package com.goya.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.goya.interview.repository")
public class ImportToolApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImportToolApplication.class, args);
    }
}
