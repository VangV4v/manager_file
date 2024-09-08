package com.vang.oauth_service.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OauthCommon {

    public static final String REDIRECT_HOME = "redirect:http://localhost:5173/home";
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
