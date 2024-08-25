package com.vang.auth_admin_service.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthResponseModel implements Serializable {

    private boolean isSuccess;
    private String jwt;
    private String role;
    private long expiration;
    private String message;
}