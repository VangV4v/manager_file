package com.vang.public_store_service.service;

import com.vang.public_store_service.model.UploadImageRequestModel;
import com.vang.public_store_service.model.UploadImageResponseModel;
import org.springframework.http.ResponseEntity;

public interface PublicStoreService {
    ResponseEntity<UploadImageResponseModel> uploadImage(UploadImageRequestModel requestModel);
}
