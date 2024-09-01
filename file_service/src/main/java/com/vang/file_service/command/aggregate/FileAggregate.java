package com.vang.file_service.command.aggregate;

import com.vang.file_service.command.command.CreateFileCommand;
import com.vang.file_service.command.command.DeleteFileCommand;
import com.vang.file_service.command.command.UpdateFileCommand;
import com.vang.file_service.command.event.FileCreatedEvent;
import com.vang.file_service.command.event.FileDeletedEvent;
import com.vang.file_service.command.event.FileUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
public class FileAggregate {

    @AggregateIdentifier
    private String aggregateId;
    private String fileId;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private String userId;
    private String userInformation;
    private String folderId;
    private String folderInformation;
    private int status;
    private String createdDate;
    private String lastModified;

    public FileAggregate() {}

    @CommandHandler
    public FileAggregate(CreateFileCommand command) {

        FileCreatedEvent event = new FileCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public FileAggregate(UpdateFileCommand command) {

        FileUpdatedEvent event = new FileUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public FileAggregate(DeleteFileCommand command) {

        FileDeletedEvent event = new FileDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void handle(FileCreatedEvent event) {

        this.aggregateId = event.getAggregateId();
        this.fileId = event.getFileId();
        this.fileName = event.getFileName();
        this.fileUrl = event.getFileUrl();
        this.fileType = event.getFileType();
        this.userId = event.getUserId();
        this.userInformation = event.getUserInformation();
        this.folderId = event.getFolderId();
        this.folderInformation = event.getFolderInformation();
        this.status = event.getStatus();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
    }

    @EventSourcingHandler
    public void handle(FileUpdatedEvent event) {

        this.aggregateId = event.getAggregateId();
        this.fileId = event.getFileId();
        this.fileName = event.getFileName();
        this.fileUrl = event.getFileUrl();
        this.fileType = event.getFileType();
        this.userId = event.getUserId();
        this.userInformation = event.getUserInformation();
        this.folderId = event.getFolderId();
        this.folderInformation = event.getFolderInformation();
        this.status = event.getStatus();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
    }

    @EventSourcingHandler
    public void handle(FileDeletedEvent event) {

        this.aggregateId = event.getAggregateId();
        this.fileId = event.getFileId();
        this.fileName = event.getFileName();
        this.fileUrl = event.getFileUrl();
        this.fileType = event.getFileType();
        this.userId = event.getUserId();
        this.userInformation = event.getUserInformation();
        this.folderId = event.getFolderId();
        this.folderInformation = event.getFolderInformation();
        this.status = event.getStatus();
        this.createdDate = event.getCreatedDate();
        this.lastModified = event.getLastModified();
    }

}