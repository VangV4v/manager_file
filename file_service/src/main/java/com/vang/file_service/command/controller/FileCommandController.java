package com.vang.file_service.command.controller;

import com.vang.file_service.command.model.FileRequestModel;
import com.vang.file_service.command.model.ResponseModel;
import com.vang.file_service.command.service.FileCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/files/")
public class FileCommandController {

    private final FileCommandService fileCommandService;

    @Autowired
    public FileCommandController(FileCommandService fileCommandService) {
        this.fileCommandService = fileCommandService;
    }

    @PostMapping(produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseModel> addFile(@ModelAttribute FileRequestModel requestModel) {

        return fileCommandService.addFile(requestModel);
    }

    @PutMapping
    public ResponseEntity<ResponseModel> updateFile(@RequestBody FileRequestModel requestModel) {

        return fileCommandService.updateFile(requestModel);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteFile(@RequestBody FileRequestModel requestModel) {

        return fileCommandService.deleteFile(requestModel);
    }
}