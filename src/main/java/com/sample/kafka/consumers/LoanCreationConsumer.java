package com.sample.kafka.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.datalayer.request.LoanCreationRequest;
import com.sample.service.LoanManagementService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@DependsOn("LoanCreationKafkaPropertiesConfig")
@Slf4j
public class LoanCreationConsumer {
    private final LoanManagementService loanManagementService;

    @Autowired
    public LoanCreationConsumer(@NonNull LoanManagementService loanManagementService){
        this.loanManagementService=loanManagementService;
    }

    @KafkaListener(topics = "#{LoanCreationKafkaPropertiesConfig.getTopicName()}",
                   groupId = "#{LoanCreationKafkaPropertiesConfig.getConsumerGroup()}",
                   containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(@Payload List<String> messages, Acknowledgment acknowledgment){
        log.info("message {}",messages);
        for (String message : messages){
            try{
                LoanCreationRequest loanCreationRequest = null;
                if(Objects.nonNull(message)){
                    ObjectMapper objectMapper = new ObjectMapper();
                    loanCreationRequest = objectMapper.convertValue(message,LoanCreationRequest.class);
                }
                loanManagementService.createLoanService(loanCreationRequest);
                log.info("[LoanCreationConsumer.listen] :: Kafka message successfully processed");
            }catch (Exception e){
                log.info("[LoanCreationConsumer.listen] :: Error while reading kafka message : ",e);
            }
        }
        acknowledgment.acknowledge();
    }
}
