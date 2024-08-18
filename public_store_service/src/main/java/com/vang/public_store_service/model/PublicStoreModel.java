package com.vang.public_store_service.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PublicStoreModel implements Serializable {

    private int id;
    private String fileName;
    private String url;
    private String createdDate;
}