package com.vang.folder_service.command.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class FolderRequestModel implements Serializable {

    private String folderId;
    private String folderName;
    private String folderPath;
    private int fileInFolder;
    private String userId;
    private String userInformation;
    private int status;
}