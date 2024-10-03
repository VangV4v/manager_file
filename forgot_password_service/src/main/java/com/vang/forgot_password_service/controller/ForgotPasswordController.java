package com.vang.forgot_password_service.controller;

import com.vang.forgot_password_service.model.ForgotPasswordRequestModel;
import com.vang.forgot_password_service.model.ResponseModel;
import com.vang.forgot_password_service.model.UpdatePasswordRequestModel;
import com.vang.forgot_password_service.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/forgot-password/")
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @Autowired
    public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> forgotPassword(@RequestBody ForgotPasswordRequestModel requestModel) {

        return forgotPasswordService.forgotPassword(requestModel);
    }

    @PostMapping("update/")
    public ResponseEntity<ResponseModel> updatePassword(@RequestBody UpdatePasswordRequestModel requestModel) {

        return forgotPasswordService.updatePassword(requestModel);
    }
}