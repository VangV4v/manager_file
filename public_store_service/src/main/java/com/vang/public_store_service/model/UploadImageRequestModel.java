package com.vang.public_store_service.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class UploadImageRequestModel implements Serializable {

    private MultipartFile fileData;
}