package com.vang.file_service.command.event;

import lombok.Data;

@Data
public class FileDeletedEvent {

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
}