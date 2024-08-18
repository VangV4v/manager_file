package com.vang.public_store_service.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UploadImageResponseModel implements Serializable {
    private boolean isSuccess;
    private String url;
    private String fileName;
}