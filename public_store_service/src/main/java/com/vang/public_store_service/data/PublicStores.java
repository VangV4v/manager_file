package com.vang.public_store_service.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("public_stores")
@Data
public class PublicStores {

    @Id
    private int id;
    private String fileName;
    private String url;
    private String createdDate;
}