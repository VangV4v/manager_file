package com.vang.auth_user_service.service.impl;

import com.vang.auth_user_service.jwt.JwtService;
import com.vang.auth_user_service.model.AuthRequestModel;
import com.vang.auth_user_service.model.AuthResponseModel;
import com.vang.auth_user_service.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final JwtService jwtService;

    @Autowired
    public AuthenticateServiceImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Future<ResponseEntity<AuthResponseModel>> authenticate(AuthRequestModel authRequestModel) {

        AuthResponseModel authResponseModel = new AuthResponseModel();
        authResponseModel.setJwt(jwtService.generateToken(authRequestModel.getUsername()));
        ResponseEntity<AuthResponseModel> response = new ResponseEntity<>(authResponseModel, HttpStatus.OK);
        return CompletableFuture.completedFuture(response);
    }

}