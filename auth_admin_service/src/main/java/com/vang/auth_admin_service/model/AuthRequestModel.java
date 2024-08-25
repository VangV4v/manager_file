package com.vang.auth_admin_service.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequestModel implements Serializable {

    private String username;
    private String password;
}