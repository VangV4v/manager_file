package com.vang.auth_user_service.common;

public class AuthUserCommon {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    public static final String MES_LOGIN_FAIL = "Invalid username or password";
    public static final String ROLE_KEY = "role";
    public static final String ROLE_VALUE = "ROLE_USER";
    public static final String IP = "192.168.160.1";
    public static final String USERNAME_KEY = "username";
    public static final String USERNAME_EXPIRATION_KEY = "userExpiration";
    public static final int GRPC_USER_PORT = 5002;
    public static final int REDIS_PORT = 6379;
    public static final String EXTENSION = "_expiration";
}