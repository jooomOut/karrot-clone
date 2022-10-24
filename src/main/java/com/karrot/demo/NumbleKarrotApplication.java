package com.karrot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@SpringBootApplication
public class NumbleKarrotApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumbleKarrotApplication.class, args);
    }
}
