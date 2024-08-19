package com.vang.public_store_service.controller;

import com.vang.public_store_service.model.UploadImageRequestModel;
import com.vang.public_store_service.model.UploadImageResponseModel;
import com.vang.public_store_service.service.PublicStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/public-store/")
public class PublicStoreController {

    private final PublicStoreService publicStoreService;

    @Autowired
    public PublicStoreController(PublicStoreService publicStoreService) {
        this.publicStoreService = publicStoreService;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UploadImageResponseModel> uploadImage(@ModelAttribute UploadImageRequestModel requestModel) {

        return publicStoreService.uploadImage(requestModel);
    }

}