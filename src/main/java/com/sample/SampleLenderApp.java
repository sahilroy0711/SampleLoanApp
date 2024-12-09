package com.sample;

import com.sample.configuration.kafka.property.LoanCreationKafkaPropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = {"com.sample.*"})
@EnableJpaRepositories(basePackages = "com.sample.repository")
@Slf4j
@PropertySource("classpath:/development/config.properties")
public class SampleLenderApp {
    public static void main(String[] args) {
        SpringApplication.run(SampleLenderApp.class, args);
        log.info("Application has started ------- ");
    }
}