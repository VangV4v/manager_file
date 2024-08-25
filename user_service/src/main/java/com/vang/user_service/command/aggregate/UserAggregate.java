package com.vang.user_service.command.aggregate;

import com.vang.user_service.command.command.CreateUserCommand;
import com.vang.user_service.command.command.DeleteUserCommand;
import com.vang.user_service.command.command.UpdateUserCommand;
import com.vang.user_service.command.event.UserCreatedEvent;
import com.vang.user_service.command.event.UserDeletedEvent;
import com.vang.user_service.command.event.UserUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class UserAggregate {

    @AggregateIdentifier
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

    public UserAggregate() {}

    @CommandHandler
    public UserAggregate(CreateUserCommand command) {

        UserCreatedEvent event = new UserCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public UserAggregate(UpdateUserCommand command) {

        UserUpdatedEvent event = new UserUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public UserAggregate(DeleteUserCommand command) {

        UserDeletedEvent event = new UserDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(UserCreatedEvent event) {

        this.aggregateId = event.getAggregateId();
        this.userId = event.getUserId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.password = event.getPassword();
        this.totalFile = event.getTotalFile();
        this.totalFolder = event.getTotalFolder();
        this.type = event.getType();
        this.status = event.getStatus();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
    }

    @EventSourcingHandler
    public void handle(UserUpdatedEvent event) {

        this.aggregateId = event.getAggregateId();
        this.userId = event.getUserId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.password = event.getPassword();
        this.totalFile = event.getTotalFile();
        this.totalFolder = event.getTotalFolder();
        this.type = event.getType();
        this.status = event.getStatus();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
    }

    @EventSourcingHandler
    public void handle(UserDeletedEvent event) {

        this.aggregateId = event.getAggregateId();
        this.userId = event.getUserId();
    }

}