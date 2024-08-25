package com.vang.user_service.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class UserCommon {

    public static final String ERR_USERNAME = "Username is already taken!";
    public static final String ERR_EMAIL = "Email is already taken!";
    public static final String ERR_SYSTEM = "There was an error in processing.";
    public static final String SUCCESS_ADD = "Add successful.";
    public static final String SUCCESS_UPDATE = "Update successful.";
    public static final String SUCCESS_DELETE = "Delete successful.";
    //Kafka
    public static final String SUBJECT = "REGISTER USER";
    public static final String REGISTER_TOPIC = "registerAccount";
    //private variable
    private static final String BASE_ID = "USER_";

    public static class Numbers {
        public static final int ZERO = 0;
    }

    public static String generateStringId() {

        StringBuilder baseId = new StringBuilder(BASE_ID);
        baseId.append(System.currentTimeMillis());
        return baseId.toString();
    }

    public static String getCurrentDate() {

        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String convertPassword(String pwd) {

        String password = "";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!pwd.startsWith("$") && getCountOfCharacters(password, "$") < 1) {

            password = passwordEncoder.encode(pwd);
        } else {

            password = pwd;
        }
        return password;
    }

    private static int getCountOfCharacters(String str, String character) {
        int count = 0;
        Arrays.stream(str.split("")).filter(value -> value.equals(character)).count();
        return count;
    }

}