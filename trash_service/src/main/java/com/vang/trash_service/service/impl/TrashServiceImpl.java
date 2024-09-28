package com.vang.trash_service.service.impl;

import com.vang.trash_service.common.UriCommon;
import com.vang.trash_service.model.request.FileRequestModel;
import com.vang.trash_service.model.response.FileResponseModel;
import com.vang.trash_service.model.response.ResponseModel;
import com.vang.trash_service.service.TrashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrashServiceImpl implements TrashService {

    private final RestTemplate restTemplate;

    @Autowired
    public TrashServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<List<FileResponseModel>> getAllByFileId() {

        FileResponseModel[] fileResponseModels = restTemplate.getForObject(UriCommon.FILE_SERVICE_TRASH_URI, FileResponseModel[].class);
        assert fileResponseModels != null : "Response is null";
        List<FileResponseModel> responseModels = Arrays.asList(fileResponseModels);
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseModel> restoreData(FileRequestModel requestModel) {

        restTemplate.put(UriCommon.FILE_SERVICE_URI, requestModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}