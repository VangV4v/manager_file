package com.vang.notification_service.common;

public class NotificationCommon {

    public static final String HOST_MAIL = "smtp.gmail.com";
    public static final int PORT_MAIL = 587;
    public static final String USER_NAME_MAIL = "replyminimicroservice@gmail.com";
    public static final String PASSWORD_MAIL = "utxz djfo ieya egfu";
    public static final String PROTOCOL_KEY = "mail.transport.protocol";
    public static final String PROTOCOL_VALUE = "smtp";
    public static final String AUTH_KEY = "mail.smtp.auth";
    public static final String AUTH_VALUE = "true";
    public static final String ENABLE_KEY = "mail.smtp.starttls.enable";
    public static final String ENABLE_VALUE = "true";
    public static final String DEBUG_KEY = "mail.debug";
    public static final String DEBUG_VALUE = "true";

    public static class TemplateCommon {

        public static final String EMAIL_VAR = "email";
        public static final String FULL_NAME_VAR = "fullName";
        public static final String TEMPLATE_REGISTER = "register_template";
        public static final String TEMPLATE_FORGOT_PASSWORD = "forgot_password_template";
        public static final String UTF8 = "UTF-8";
    }
}