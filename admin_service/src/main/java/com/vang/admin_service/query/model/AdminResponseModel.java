package com.vang.admin_service.query.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminResponseModel implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private int status;
    private String createdDate;
    private String lastModified;

    public void initEmptyObject() {
        this.id = null;
        this.firstName = null;
        this.lastName = null;
        this.username = null;
        this.email = null;
        this.password = null;
        this.status = -1;
        this.createdDate = null;
        this.lastModified = null;
    }
}