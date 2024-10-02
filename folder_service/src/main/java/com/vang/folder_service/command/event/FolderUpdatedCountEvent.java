package com.vang.folder_service.command.event;

import lombok.Data;

@Data
public class FolderUpdatedCountEvent {

    private String aggregateId = System.currentTimeMillis()+"";
    private String folderId;
    private String folderName;
    private String folderPath;
    private int fileInFolder;
    private String userId;
    private String userInformation;
    private int status;
    private String createdDate;
    private String lastModified;
    private int fileInTrash;
}