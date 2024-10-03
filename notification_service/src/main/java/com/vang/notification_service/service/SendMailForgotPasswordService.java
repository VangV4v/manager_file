package com.vang.notification_service.service;

import com.vang.notification_service.common.NotificationCommon;
import com.vang.notification_service.model.ForgotPasswordMessageModel;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Base64;

@Service
public class SendMailForgotPasswordService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public SendMailForgotPasswordService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendForgotPassword(ForgotPasswordMessageModel messageModel) {

        try {

            String emailEncoded = Base64.getEncoder().encodeToString(messageModel.getEmail().getBytes());
            System.out.println(emailEncoded);
            Context context = new Context();
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            context.setVariable(NotificationCommon.TemplateCommon.EMAIL_VAR, messageModel.getEmail());
            context.setVariable("condition", "http://localhost:5173/forgot-password?action=reset&email="+emailEncoded);
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, NotificationCommon.TemplateCommon.UTF8);
            String htmlTemplate = templateEngine.process(NotificationCommon.TemplateCommon.TEMPLATE_FORGOT_PASSWORD, context);
            message.setTo(messageModel.getEmail());
            message.setSubject("RESET PASSWORD");
            message.setText(htmlTemplate, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}