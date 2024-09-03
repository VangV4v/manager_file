package com.vang.public_store_service.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceCommon {
    public static final String BUCKET = "publicstore";
    public static final String END_POINT = "http://192.168.160.1:9000";
    public static final String USERNAME = "minioadmin";
    public static final String PASSWORD = "minioadmin";

    public static String getCurrentDate() {

        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}