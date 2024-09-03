package com.vang.folder_service.command.event;

import lombok.Data;

@Data
public class FolderDeletedEvent {

    private String aggregateId;
    private String folderId;
    private String userId;
}