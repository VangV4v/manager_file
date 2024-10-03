package com.vang.forgot_password_service.service;

import com.vang.forgot_password_service.model.ForgotPasswordRequestModel;
import com.vang.forgot_password_service.model.ResponseModel;
import com.vang.forgot_password_service.model.UpdatePasswordRequestModel;
import org.springframework.http.ResponseEntity;

public interface ForgotPasswordService {
    ResponseEntity<ResponseModel> forgotPassword(ForgotPasswordRequestModel requestModel);
    ResponseEntity<ResponseModel> updatePassword(UpdatePasswordRequestModel requestModel);
}