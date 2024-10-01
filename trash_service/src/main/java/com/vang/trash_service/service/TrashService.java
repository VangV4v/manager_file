package com.vang.trash_service.service;

import com.vang.trash_service.model.request.FileRequestModel;
import com.vang.trash_service.model.response.FileResponseModel;
import com.vang.trash_service.model.response.ResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrashService {
    ResponseEntity<List<FileResponseModel>> getAllByFileId();

    ResponseEntity<ResponseModel> restoreData(FileRequestModel requestModel);

    ResponseEntity<ResponseModel> deleteData(FileRequestModel requestModel);
}