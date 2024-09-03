package com.vang.file_service.query.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FindByUserId {

    private String userId;
    private String folderId;
}