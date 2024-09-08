package com.vang.oauth_service.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OauthCommon {

    public static final String REDIRECT_HOME = "redirect:http://localhost:5173/process-oauth2";
    public static final String GIVEN_NAME = "given_name";
    public static final String FAMILY_NAME = "family_name";
    public static final String EMAIL = "email";
    public static final String SUB = "sub";
    public static final String IP = "192.168.160.1";
    public static final int GRPC_AUTH_USER_PORT = 7002;
    //private variable
    private static final String BASE_ID = "USER_";
    public static String generateStringId() {

        StringBuilder baseId = new StringBuilder(BASE_ID);
        baseId.append(System.currentTimeMillis());
        return baseId.toString();
    }

    public static String getCurrentDate() {

        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
