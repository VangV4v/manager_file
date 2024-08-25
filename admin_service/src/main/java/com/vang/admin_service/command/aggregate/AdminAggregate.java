package com.vang.admin_service.command.aggregate;

import com.vang.admin_service.command.command.CreateAdminCommand;
import com.vang.admin_service.command.command.DeleteAdminCommand;
import com.vang.admin_service.command.command.UpdateAdminCommand;
import com.vang.admin_service.command.event.AdminCreatedEvent;
import com.vang.admin_service.command.event.AdminDeletedEvent;
import com.vang.admin_service.command.event.AdminUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class AdminAggregate {

    @AggregateIdentifier
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

    public AdminAggregate() {}

    @CommandHandler
    public AdminAggregate(CreateAdminCommand command) {
        AdminCreatedEvent event = new AdminCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public AdminAggregate(UpdateAdminCommand command) {
        AdminUpdatedEvent event = new AdminUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public AdminAggregate(DeleteAdminCommand command) {
        AdminDeletedEvent event = new AdminDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(AdminCreatedEvent event) {

        this.aggregateId = event.getAggregateId();
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.password = event.getPassword();
        this.status = event.getStatus();
        this.isSuccess = event.isSuccess();
    }

    @EventSourcingHandler
    public void handle(AdminUpdatedEvent event) {

        this.aggregateId = event.getAggregateId();
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.password = event.getPassword();
        this.status = event.getStatus();
        this.isSuccess = event.isSuccess();
    }

    @EventSourcingHandler
    public void handle(AdminDeletedEvent event) {

        this.aggregateId = event.getAggregateId();
        this.id = event.getId();
        this.isSuccess = event.isSuccess();
    }
}