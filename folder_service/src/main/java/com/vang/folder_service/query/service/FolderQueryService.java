package com.vang.folder_service.query.service;

import com.vang.folder_service.query.model.FolderResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FolderQueryService {

    ResponseEntity<List<FolderResponseModel>> findByUserId();

    ResponseEntity<FolderResponseModel> findByFolderId(String folderId);

    ResponseEntity<List<FolderResponseModel>> findAllFolders();
}