package com.vang.file_service.command.service;

import com.vang.file_service.command.model.FileRequestModel;
import com.vang.file_service.command.model.ResponseModel;
import org.springframework.http.ResponseEntity;

public interface FileCommandService {

    ResponseEntity<ResponseModel> addFile(FileRequestModel requestModel);
    ResponseEntity<ResponseModel> updateFile(FileRequestModel requestModel);
    ResponseEntity<ResponseModel> deleteFile(FileRequestModel requestModel);
}