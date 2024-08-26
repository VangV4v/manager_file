package com.vang.folder_service.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class UpdateFolderCommand {

    @TargetAggregateIdentifier
    private String aggregateId = System.currentTimeMillis()+"";
    private String folderId;
    private String folderName;
    private String folderPath;
    private int fileInFolder;
    private String userId;
    private String userInformation;
    private int status;
}