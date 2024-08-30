package com.vang.folder_service.command.controller;

import com.vang.folder_service.command.model.FolderRequestModel;
import com.vang.folder_service.command.model.ResponseModel;
import com.vang.folder_service.command.service.FolderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/folders/")
public class FolderCommandController {

    private final FolderCommandService folderCommandService;

    @Autowired
    public FolderCommandController(FolderCommandService folderCommandService) {
        this.folderCommandService = folderCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> addFolder(@RequestBody FolderRequestModel requestModel) {

        return folderCommandService.addFolder(requestModel);
    }
}
