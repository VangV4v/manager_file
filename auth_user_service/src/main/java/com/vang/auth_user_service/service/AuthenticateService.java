package com.vang.auth_user_service.service;

import com.vang.auth_user_service.model.AuthRequestModel;
import com.vang.auth_user_service.model.AuthResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Future;

public interface AuthenticateService {
   Future<ResponseEntity<AuthResponseModel>> authenticate(AuthRequestModel authRequestModel);
}