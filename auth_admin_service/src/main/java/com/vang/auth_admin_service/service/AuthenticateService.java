package com.vang.auth_admin_service.service;

import com.vang.auth_admin_service.model.AuthRequestModel;
import com.vang.auth_admin_service.model.AuthResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Future;

public interface AuthenticateService {

    Future<ResponseEntity<AuthResponseModel>> authenticate(AuthRequestModel requestModel);
}