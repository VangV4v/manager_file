package com.vang.admin_service.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class DeleteAdminCommand {

    @TargetAggregateIdentifier
    private String aggregateId = System.currentTimeMillis()+"";
    private String id;
    private boolean isSuccess = false;
}