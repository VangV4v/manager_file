package com.vang.file_service.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class FileCommon {

    //Minio
    public static final String BUCKET = "userstore";
    public static final String END_POINT = "http://192.168.160.1:9000";
    public static final String USERNAME = "minioadmin";
    public static final String PASSWORD = "minioadmin";
    //end Minio
    public static final String SUCCESS_ADD = "Create file successful";
    public static final String SUCCESS_UPDATE = "Update file successful";
    public static final String SUCCESS_DELETE = "Delete file successful";
    public static final String IP = "192.168.160.1";
    public static final int GRPC_AUTH_USER_PORT = 7002;
    public static final int GRPC_USER_PORT = 5002;
    public static final int GRPC_FOLDER_PORT = 7004;
    public static final int GRPC_GATEWAY_PORT = 2003;
    //private variable
    private static final String BASE_ID = "FILE_";

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