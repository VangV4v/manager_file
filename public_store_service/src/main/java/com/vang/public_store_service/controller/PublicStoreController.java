package com.vang.public_store_service.controller;

import com.vang.public_store_service.model.UploadImageRequestModel;
import com.vang.public_store_service.model.UploadImageResponseModel;
import com.vang.public_store_service.service.PublicStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public-store/")
public class PublicStoreController {

    private final PublicStoreService publicStoreService;

    @Autowired
    public PublicStoreController(PublicStoreService publicStoreService) {
        this.publicStoreService = publicStoreService;
    }

    @PostMapping
    public ResponseEntity<UploadImageResponseModel> uploadImage(@ModelAttribute UploadImageRequestModel requestModel) {

        return publicStoreService.uploadImage(requestModel);
    }

}