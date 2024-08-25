package com.vang.admin_service.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class AdminCommon {

    public static final String ERR_SYSTEM = "There was an error in processing.";
    public static final String ERR_EXIST_EMAIL = "Email already exist.";
    public static final String ERR_EXIST_USERNAME = "Username already exist.";
    public static final String SUCCESS_ADD = "Add successful.";
    public static final String SUCCESS_UPDATE = "Update successful.";
    public static final String SUCCESS_DELETE = "Delete successful.";
    //private variable
    private static final String BASE_ID = "ADMIN_";

    public static String generateStringId() {

        StringBuilder baseId = new StringBuilder(BASE_ID);
        baseId.append(System.currentTimeMillis());
        return baseId.toString();
    }

    public static String getCurrentDate() {

        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    //Number utils
    public static class Numbers {
        public static final int ONE = 1;
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