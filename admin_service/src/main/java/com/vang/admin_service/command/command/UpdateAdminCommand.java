package com.vang.admin_service.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class UpdateAdminCommand {

    @TargetAggregateIdentifier
    private String aggregateId = System.currentTimeMillis()+"";
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