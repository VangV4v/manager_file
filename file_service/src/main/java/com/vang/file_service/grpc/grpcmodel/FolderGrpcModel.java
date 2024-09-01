package com.vang.file_service.grpc.grpcmodel;

import lombok.Data;

import java.io.Serializable;

@Data
public class FolderGrpcModel implements Serializable {

    private String folderId;
    private String folderName;
    private String folderPath;
    private String createdDate;
    private String lastModified;
}