package com.vang.trash_service.controller;

import com.vang.trash_service.model.request.FileRequestModel;
import com.vang.trash_service.model.response.FileResponseModel;
import com.vang.trash_service.model.response.ResponseModel;
import com.vang.trash_service.service.TrashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trash/")
public class TrashController {

    private final TrashService trashService;

    @Autowired
    public TrashController(TrashService trashService) {
        this.trashService = trashService;
    }

    @GetMapping("file/")
    public ResponseEntity<List<FileResponseModel>> getFileById() {

        return trashService.getAllByFileId();
    }

    @PutMapping("restore/")
    public ResponseEntity<ResponseModel> restoreData(@RequestBody FileRequestModel requestModel) {

        return trashService.restoreData(requestModel);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteData(@RequestBody FileRequestModel requestModel) {
        return trashService.deleteData(requestModel);
    }
}