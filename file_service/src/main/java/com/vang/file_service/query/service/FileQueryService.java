package com.vang.file_service.query.service;

import com.vang.file_service.query.model.FileResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FileQueryService {

    ResponseEntity<FileResponseModel> findByFileId(String fileId);
    ResponseEntity<List<FileResponseModel>> findAllFiles();
    ResponseEntity<List<FileResponseModel>> findByUserId(String folderId);
}