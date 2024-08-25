package com.vang.user_service.command.event;

import lombok.Data;

@Data
public class UserUpdatedEvent {

    private String aggregateId;
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