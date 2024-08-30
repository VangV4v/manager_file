package com.vang.folder_service.command.aggregate;

import com.vang.folder_service.command.command.CreateFolderCommand;
import com.vang.folder_service.command.event.FolderCreatedEvent;
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

    public FolderAggregate() {}

    @CommandHandler
    public FolderAggregate(CreateFolderCommand command) {

        FolderCreatedEvent event = new FolderCreatedEvent();
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
    }

}