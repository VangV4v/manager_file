package com.vang.forgot_password_service.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdatePasswordRequestModel implements Serializable {

    private String email;
    private String password;
}