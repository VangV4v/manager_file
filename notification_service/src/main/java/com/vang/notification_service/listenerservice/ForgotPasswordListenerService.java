package com.vang.notification_service.listenerservice;

import com.vang.notification_service.model.ForgotPasswordMessageModel;
import com.vang.notification_service.service.SendMailForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordListenerService {

    private final SendMailForgotPasswordService sendMailForgotPasswordService;

    @Autowired
    public ForgotPasswordListenerService(SendMailForgotPasswordService sendMailForgotPasswordService) {
        this.sendMailForgotPasswordService = sendMailForgotPasswordService;
    }

    @KafkaListener(topics = "forgotPassword", groupId = "forgotPassword")
    public void handleForgotPassword(ForgotPasswordMessageModel messageModel) {

        sendMailForgotPasswordService.sendForgotPassword(messageModel);
    }
}