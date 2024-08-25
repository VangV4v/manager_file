package com.vang.notification_service.service;

import com.vang.notification_service.common.NotificationCommon;
import com.vang.notification_service.model.RegisterMessageModel;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class SendMailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public SendMailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void notifyRegistrationAccount(RegisterMessageModel messageModel) {

        try {

            Context context = new Context();
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            context.setVariable(NotificationCommon.TemplateCommon.FULL_NAME_VAR, messageModel.getFullName());
            context.setVariable(NotificationCommon.TemplateCommon.EMAIL_VAR, messageModel.getEmail());
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, NotificationCommon.TemplateCommon.UTF8);
            String htmlTemplate = templateEngine.process(NotificationCommon.TemplateCommon.TEMPLATE_REGISTER, context);
            message.setTo(messageModel.getEmail());
            message.setSubject(messageModel.getSubject());
            message.setText(htmlTemplate, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}