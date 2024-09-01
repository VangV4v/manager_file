package com.vang.file_service.query.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class FileResponseModel implements Serializable {

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

    public void initialize() {

        this.fileId = null;
        this.fileName = null;
        this.fileUrl = null;
        this.fileType = null;
        this.userId = null;
        this.userInformation = null;
        this.folderId = null;
        this.folderInformation = null;
        this.status = -1;
        this.createdDate = null;
        this.lastModified = null;
    }
}