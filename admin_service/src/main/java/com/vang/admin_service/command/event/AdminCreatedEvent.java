package com.vang.admin_service.command.event;

import lombok.Data;

@Data
public class AdminCreatedEvent {

    private String aggregateId;
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private int status;
    private String createdDate;
    private String lastModified;
    private boolean isSuccess = false;
}