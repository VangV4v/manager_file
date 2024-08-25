package com.vang.user_service.query.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class UserResponseModel implements Serializable {

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

    public void initDefault() {

        this.userId = null;
        this.firstName = null;
        this.lastName = null;
        this.username = null;
        this.email = null;
        this.password = null;
        this.totalFile = -1;
        this.totalFolder = -1;
        this.type = -1;
        this.status = -1;
        this.createdDate = null;
        this.lastModified = null;
    }

}