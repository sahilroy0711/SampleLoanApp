package com.sample.configuration.kafka.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("spring.kafka")
public class LoanCreationKafkaPropertiesConfig {

    private String bootstrapServers;
    private String topicName;
    private String consumerGroup;
}
