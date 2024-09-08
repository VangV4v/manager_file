package com.vang.oauth_service.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users {

    @Id
    @Column(name = "userid")
    private String userId;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "totalfile")
    private int totalFile;
    @Column(name = "totalfolder")
    private int totalFolder;
    @Column(name = "type")
    private int type;
    @Column(name = "status")
    private int status;
    @Column(name = "createddate")
    private String createdDate;
    @Column(name = "lastmodified")
    private String lastModified;
}