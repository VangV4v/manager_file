package com.vang.file_service.command.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.io.Serializable;

@Data
public class FileRequestModel implements Serializable {

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
    private MultipartFile fileData;
}