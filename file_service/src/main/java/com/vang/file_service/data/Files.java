package com.vang.file_service.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "files")
@Data
public class Files {

    @Id
    @Column(name = "fileid")
    private String fileId;
    @Column(name = "filename")
    private String fileName;
    @Column(name = "fileurl")
    private String fileUrl;
    @Column(name = "filetype")
    private String fileType;
    @Column(name = "userid")
    private String userId;
    @Column(name = "userinformation")
    private String userInformation;
    @Column(name = "folderid")
    private String folderId;
    @Column(name = "folderinformation")
    private String folderInformation;
    @Column(name = "status")
    private int status;
    @Column(name = "createddate")
    private String createdDate;
    @Column(name = "lastmodified")
    private String lastModified;
}