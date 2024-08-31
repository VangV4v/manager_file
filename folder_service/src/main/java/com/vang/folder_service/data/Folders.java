package com.vang.folder_service.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "folders")
@Data
public class Folders {

    @Id
    @Column(name = "folderid")
    private String folderId;
    @Column(name = "foldername")
    private String folderName;
    @Column(name = "folderpath")
    private String folderPath;
    @Column(name = "fileinfolder")
    private int fileInFolder;
    @Column(name = "userid")
    private String userId;
    @Column(name = "userinformation")
    private String userInformation;
    @Column(name = "status")
    private int status;
    @Column(name = "createddate")
    private String createdDate;
    @Column(name = "lastmodified")
    private String lastModified;
}