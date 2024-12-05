package com.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = {"com.sample.*"})
@EnableJpaRepositories(basePackages = "com.sample.repository")
@EntityScan(basePackages = "com.sample.model")
@Slf4j// Ensure the entity package is included
public class SampleLenderApp {
    public static void main(String[] args) {
        SpringApplication.run(SampleLenderApp.class, args);
        log.info("Application has started -------- 453");
    }
}