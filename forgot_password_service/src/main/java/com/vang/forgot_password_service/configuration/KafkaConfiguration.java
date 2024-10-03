package com.vang.forgot_password_service.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    NewTopic forgotPasswordTopic() {
        return new NewTopic("forgotPassword", 2, (short) 1);
    }
}