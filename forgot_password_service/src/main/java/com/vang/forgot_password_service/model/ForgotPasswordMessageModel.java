package com.vang.forgot_password_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class ForgotPasswordMessageModel implements Serializable {

    private String email;
}