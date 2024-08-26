package com.vang.folder_service.command.service;

import com.vang.folder_service.command.model.FolderRequestModel;
import com.vang.folder_service.command.model.ResponseModel;
import org.springframework.http.ResponseEntity;

public interface FolderCommandService {
    ResponseEntity<ResponseModel> addFolder(FolderRequestModel requestModel);
    ResponseEntity<ResponseModel> updateFolder(FolderRequestModel requestModel);
    ResponseEntity<ResponseModel> deleteFolder(FolderRequestModel requestModel);
}