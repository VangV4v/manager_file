package com.vang.trash_service.model.response;

import lombok.Data;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class ResponseModel implements Serializable {

    private boolean isSuccess = false;
    private String message;
    private Set<String> errors = new HashSet<>();
}