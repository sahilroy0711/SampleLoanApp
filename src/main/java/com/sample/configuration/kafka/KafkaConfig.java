package com.sample.configuration.kafka;

import com.sample.configuration.kafka.property.LoanCreationKafkaPropertiesConfig;
import lombok.NonNull;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    private final LoanCreationKafkaPropertiesConfig loanCreationKafkaPropertiesConfig;

    @Autowired
    public KafkaConfig(@NonNull LoanCreationKafkaPropertiesConfig loanCreationKafkaPropertiesConfig){
        this.loanCreationKafkaPropertiesConfig=loanCreationKafkaPropertiesConfig;
        System.out.println("LoanCreationKafkaPropertiesConfig initialized: " + loanCreationKafkaPropertiesConfig);
    }
    @Bean
    public Map<String,Object> LoanCreationConsumerConfigs(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,loanCreationKafkaPropertiesConfig.getBootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,loanCreationKafkaPropertiesConfig.getConsumerGroup());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,500);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG,1048576*8);
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> LoanCreationConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(LoanCreationConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(LoanCreationConsumerFactory());
        factory.setBatchListener(true);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
}
