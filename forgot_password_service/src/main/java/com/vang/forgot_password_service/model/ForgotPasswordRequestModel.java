package com.vang.forgot_password_service.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ForgotPasswordRequestModel implements Serializable {

    private String email;
}