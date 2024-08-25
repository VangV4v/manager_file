package com.vang.user_service.command.event;

import lombok.Data;

@Data
public class UserDeletedEvent {

    private String aggregateId;
    private String userId;
}