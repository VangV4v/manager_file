package com.vang.user_service.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic registerUser() {

        return new NewTopic("registerAccount", 2, (short) 1);
    }

}