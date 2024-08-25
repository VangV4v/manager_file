package com.vang.notification_service.configuration;

import com.vang.notification_service.common.NotificationCommon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class BeanConfiguration {

    @Bean
    public JsonMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @Bean
    public JavaMailSender mailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(NotificationCommon.HOST_MAIL);
        mailSender.setPort(NotificationCommon.PORT_MAIL);

        mailSender.setUsername(NotificationCommon.USER_NAME_MAIL);
        mailSender.setPassword(NotificationCommon.PASSWORD_MAIL);

        Properties props = mailSender.getJavaMailProperties();
        props.put(NotificationCommon.PROTOCOL_KEY, NotificationCommon.PROTOCOL_VALUE);
        props.put(NotificationCommon.AUTH_KEY, NotificationCommon.AUTH_VALUE);
        props.put(NotificationCommon.ENABLE_KEY, NotificationCommon.ENABLE_VALUE);
        props.put(NotificationCommon.DEBUG_KEY, NotificationCommon.DEBUG_VALUE);
        return mailSender;
    }

}