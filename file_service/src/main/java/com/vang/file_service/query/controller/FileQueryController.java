package com.vang.file_service.query.controller;

import com.vang.file_service.query.model.FileResponseModel;
import com.vang.file_service.query.service.FileQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files/")
public class FileQueryController {

    private final FileQueryService fileQueryService;

    @Autowired
    public FileQueryController(FileQueryService fileQueryService) {

        this.fileQueryService = fileQueryService;
    }

    @GetMapping("{id}/")
    public ResponseEntity<FileResponseModel> findByFileId(@PathVariable("id") String fileId) {

        return fileQueryService.findByFileId(fileId);
    }

    @GetMapping
    public ResponseEntity<List<FileResponseModel>> findAll() {

        return fileQueryService.findAllFiles();
    }

    @GetMapping("folder/{folderId}/")
    public ResponseEntity<List<FileResponseModel>> findByUserId(@PathVariable("folderId") String folderId) {

        return fileQueryService.findByUserId(folderId);
    }

    @GetMapping("trash/")
    public ResponseEntity<List<FileResponseModel>> findAllByStatusDelete() {

        return fileQueryService.findAllByStatusDelete();
    }

}