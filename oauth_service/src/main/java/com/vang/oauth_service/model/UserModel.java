package com.vang.oauth_service.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserModel {

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