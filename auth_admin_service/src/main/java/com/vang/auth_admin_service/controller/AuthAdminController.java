package com.vang.auth_admin_service.controller;

import com.vang.auth_admin_service.model.AuthRequestModel;
import com.vang.auth_admin_service.model.AuthResponseModel;
import com.vang.auth_admin_service.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/v1/auth/admin/")
public class AuthAdminController {

    private final AuthenticateService authenticateService;

    @Autowired
    public AuthAdminController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @PostMapping
    public Future<ResponseEntity<AuthResponseModel>> authenticate(@RequestBody AuthRequestModel authRequestModel) {

        return authenticateService.authenticate(authRequestModel);
    }

}