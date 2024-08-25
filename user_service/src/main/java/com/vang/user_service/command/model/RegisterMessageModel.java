package com.vang.user_service.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterMessageModel implements Serializable {

    private String subject;
    private String fullName;
    private String email;
}