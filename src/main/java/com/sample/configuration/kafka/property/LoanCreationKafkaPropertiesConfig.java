package com.sample.configuration.kafka.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration("LoanCreationKafkaPropertiesConfig")
@ConfigurationProperties("spring.kafka")
public class LoanCreationKafkaPropertiesConfig {

    private String bootstrapServers;
    private String topicName;
    private String consumerGroup;
}
