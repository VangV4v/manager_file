package com.vang.notification_service.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterMessageModel implements Serializable {

    private String subject;
    private String fullName;
    private String email;
}