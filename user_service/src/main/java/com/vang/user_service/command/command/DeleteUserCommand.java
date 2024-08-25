package com.vang.user_service.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteUserCommand {

    @TargetAggregateIdentifier
    private String aggregateId = System.currentTimeMillis()+"";
    private String userId;
}