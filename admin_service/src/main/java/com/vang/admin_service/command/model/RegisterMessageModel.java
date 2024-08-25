package com.vang.admin_service.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMessageModel implements Serializable {

    private String subject;
    private String fullName;
    private String email;
}