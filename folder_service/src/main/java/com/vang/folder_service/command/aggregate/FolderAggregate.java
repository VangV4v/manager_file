package com.vang.folder_service.command.aggregate;

import com.vang.folder_service.command.command.CreateFolderCommand;
import com.vang.folder_service.command.command.DeleteFolderCommand;
import com.vang.folder_service.command.command.UpdateFolderCommand;
import com.vang.folder_service.command.event.FolderCreatedEvent;
import com.vang.folder_service.command.event.FolderDeletedEvent;
import com.vang.folder_service.command.event.FolderUpdatedEvent;
import com.vang.folder_service.command.model.FolderResponseModel;
import com.vang.folder_service.command.sharedata.SharedData;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class FolderAggregate {

    @AggregateIdentifier
    private String aggregateId;
    private String folderId;
    private String folderName;
    private String folderPath;
    private int fileInFolder;
    private String userId;
    private String userInformation;
    private int status;
    private String createdDate;
    private String lastModified;

    public FolderAggregate() {
    }

    @CommandHandler
    public FolderAggregate(CreateFolderCommand command) {

        FolderCreatedEvent event = new FolderCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public FolderAggregate(UpdateFolderCommand command) {

        FolderUpdatedEvent event = new FolderUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public FolderAggregate(DeleteFolderCommand command) {

        FolderDeletedEvent event = new FolderDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(FolderCreatedEvent event) {

        this.aggregateId = event.getAggregateId();
        this.folderId = event.getFolderId();
        this.folderName = event.getFolderName();
        this.folderPath = event.getFolderPath();
        this.fileInFolder = event.getFileInFolder();
        this.userId = event.getUserId();
        this.userInformation = event.getUserInformation();
        this.status = event.getStatus();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
        FolderResponseModel responseModel = new FolderResponseModel();
        BeanUtils.copyProperties(event, responseModel);
        SharedData.setInstance(responseModel);
    }

    @EventSourcingHandler
    public void handle(FolderUpdatedEvent event) {

        this.aggregateId = event.getAggregateId();
        this.folderId = event.getFolderId();
        this.folderName = event.getFolderName();
        this.folderPath = event.getFolderPath();
        this.fileInFolder = event.getFileInFolder();
        this.userId = event.getUserId();
        this.userInformation = event.getUserInformation();
        this.status = event.getStatus();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
        FolderResponseModel responseModel = new FolderResponseModel();
        BeanUtils.copyProperties(event, responseModel);
        SharedData.setInstance(responseModel);
    }

    @EventSourcingHandler
    public void handle(FolderDeletedEvent event) {

        this.aggregateId = event.getAggregateId();
        this.folderId = event.getFolderId();
        this.userId = event.getUserId();
    }
}