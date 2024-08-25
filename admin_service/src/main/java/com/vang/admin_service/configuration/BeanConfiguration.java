package com.vang.admin_service.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public NewTopic registerAccount() {
        return new NewTopic("registerAccount", 1, (short) 1);
    }

}