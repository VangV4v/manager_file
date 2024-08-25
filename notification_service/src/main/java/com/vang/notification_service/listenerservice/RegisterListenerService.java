package com.vang.notification_service.listenerservice;

import com.vang.notification_service.model.RegisterMessageModel;
import com.vang.notification_service.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RegisterListenerService {

    private final SendMailService mailService;

    @Autowired
    public RegisterListenerService(SendMailService mailService) {
        this.mailService = mailService;
    }

    @KafkaListener(topics = "registerAccount", groupId = "registerAccount")
    public void registerAccount(RegisterMessageModel messageModel) {
        mailService.notifyRegistrationAccount(messageModel);
    }

}