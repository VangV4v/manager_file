package com.vang.auth_user_service.service.impl;

import com.vang.auth_user_service.common.AuthUserCommon;
import com.vang.auth_user_service.jwt.JwtService;
import com.vang.auth_user_service.model.AuthRequestModel;
import com.vang.auth_user_service.model.AuthResponseModel;
import com.vang.auth_user_service.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticateServiceImpl(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Future<ResponseEntity<AuthResponseModel>> authenticate(AuthRequestModel requestModel) {

        AuthResponseModel authResponseModel = new AuthResponseModel();
        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword()));
            if(authentication.isAuthenticated()) {

                authResponseModel.setJwt(jwtService.generateToken(requestModel.getUsername()));
                authResponseModel.setRole(AuthUserCommon.ROLE_VALUE);
                authResponseModel.setSuccess(Boolean.TRUE);
                authResponseModel.setExpiration(System.currentTimeMillis()+ (1000 * 60 * 30));
                ResponseEntity<AuthResponseModel> response = new ResponseEntity<>(authResponseModel, HttpStatus.OK);
                return CompletableFuture.completedFuture(response);
            }
        } catch (BadCredentialsException e) {

            authResponseModel.setSuccess(Boolean.FALSE);
            authResponseModel.setMessage(AuthUserCommon.MES_LOGIN_FAIL);
            ResponseEntity<AuthResponseModel> response = new ResponseEntity<>(authResponseModel, HttpStatus.UNAUTHORIZED);
            return CompletableFuture.completedFuture(response);
        }
        return null;
    }
}