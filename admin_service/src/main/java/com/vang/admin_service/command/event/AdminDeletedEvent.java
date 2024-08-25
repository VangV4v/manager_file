package com.vang.admin_service.command.event;

import lombok.Data;

@Data
public class AdminDeletedEvent {

    private String aggregateId;
    private String id;
    private boolean isSuccess = false;
}