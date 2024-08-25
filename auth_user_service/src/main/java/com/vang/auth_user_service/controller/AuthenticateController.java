package com.vang.auth_user_service.controller;

import com.vang.auth_user_service.model.AuthRequestModel;
import com.vang.auth_user_service.model.AuthResponseModel;
import com.vang.auth_user_service.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/v1/auth/user/")
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    @Autowired
    public AuthenticateController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @PostMapping
    public Future<ResponseEntity<AuthResponseModel>> authenticate(@RequestBody AuthRequestModel requestModel) {

        return authenticateService.authenticate(requestModel);
    }

}