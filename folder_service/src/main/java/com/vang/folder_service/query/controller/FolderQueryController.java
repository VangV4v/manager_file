package com.vang.folder_service.query.controller;

import com.vang.folder_service.query.model.FolderResponseModel;
import com.vang.folder_service.query.service.FolderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/folders/")
public class FolderQueryController {

    private final FolderQueryService folderQueryService;

    @Autowired
    public FolderQueryController(FolderQueryService folderQueryService) {
        this.folderQueryService = folderQueryService;
    }

    @GetMapping("/{id}/")
    public ResponseEntity<FolderResponseModel> findByFolderId(@PathVariable("id") String folderId) {

        return folderQueryService.findByFolderId(folderId);
    }

    @GetMapping("user/")
    public ResponseEntity<List<FolderResponseModel>> findByUserId() {

        return folderQueryService.findByUserId();
    }

    @GetMapping
    public ResponseEntity<List<FolderResponseModel>> findAll() {

        return folderQueryService.findAllFolders();
    }
}
