package com.vang.user_service.command.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequestModel implements Serializable {

    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private int totalFile;
    private int totalFolder;
    private int type;
    private int status;
    private String createdDate;
    private String lastModified;
}