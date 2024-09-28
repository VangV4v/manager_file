package com.vang.trash_service.model.response;

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
}