package com.vang.folder_service.command.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class FolderResponseModel implements Serializable {

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

    public FolderResponseModel() {

        this.folderId = null;
        this.folderName = null;
        this.folderPath = null;
        this.fileInFolder = -1;
        this.userId = null;
        this.userInformation = null;
        this.status = -1;
        this.createdDate = null;
        this.lastModified = null;
        this.fileInTrash = -1;
    }
}