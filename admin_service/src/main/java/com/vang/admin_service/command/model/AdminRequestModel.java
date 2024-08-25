package com.vang.admin_service.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminRequestModel implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private int status;
    private String createdDate;
    private String lastModified;
}