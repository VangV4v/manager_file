package com.vang.folder_service.command.aggregate;

import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

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
}