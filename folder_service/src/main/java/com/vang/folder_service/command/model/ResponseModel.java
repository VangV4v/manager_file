package com.vang.folder_service.command.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class ResponseModel implements Serializable {

    private boolean isSuccess = false;
    private String message;
    private FolderResponseModel folderData = null;
    private Set<String> errors = new HashSet<>();
}