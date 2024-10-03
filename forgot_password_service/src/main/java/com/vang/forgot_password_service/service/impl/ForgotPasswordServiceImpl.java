package com.vang.forgot_password_service.service.impl;

import com.vang.forgot_password_service.grpc.grpc.CheckEmailForgotPasswordImpl;
import com.vang.forgot_password_service.grpc.grpc.UpdatePasswordImpl;
import com.vang.forgot_password_service.model.ForgotPasswordMessageModel;
import com.vang.forgot_password_service.model.ForgotPasswordRequestModel;
import com.vang.forgot_password_service.model.ResponseModel;
import com.vang.forgot_password_service.model.UpdatePasswordRequestModel;
import com.vang.forgot_password_service.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final CheckEmailForgotPasswordImpl checkEmailForgotPassword;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final UpdatePasswordImpl updatePasswordImpl;

    @Autowired
    public ForgotPasswordServiceImpl(CheckEmailForgotPasswordImpl checkEmailForgotPassword, KafkaTemplate<String, Object> kafkaTemplate, UpdatePasswordImpl updatePasswordImpl) {
        this.checkEmailForgotPassword = checkEmailForgotPassword;
        this.kafkaTemplate = kafkaTemplate;
        this.updatePasswordImpl = updatePasswordImpl;
    }

    @Override
    public ResponseEntity<ResponseModel> forgotPassword(ForgotPasswordRequestModel requestModel) {

        ResponseModel responseModel = new ResponseModel();
        ForgotPasswordMessageModel messageModel = new ForgotPasswordMessageModel();
        messageModel.setEmail(requestModel.getEmail());
        boolean isExistEmail = checkEmailForgotPassword.checkExistEmail(requestModel.getEmail());
        if (isExistEmail) {

            responseModel.setSuccess(Boolean.TRUE);
            responseModel.setMessage("Please check your email");
            kafkaTemplate.send("forgotPassword", messageModel);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);

        } else {

            responseModel.setSuccess(Boolean.FALSE);
            responseModel.setMessage("Email is not exist");
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> updatePassword(UpdatePasswordRequestModel requestModel) {

        ResponseModel responseModel = new ResponseModel();
        boolean updatePassword = updatePasswordImpl.updatePassword(requestModel);
        if (updatePassword) {

            responseModel.setSuccess(Boolean.TRUE);
            responseModel.setMessage("Password updated successfully");
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } else {

            responseModel.setSuccess(Boolean.FALSE);
            responseModel.setMessage("Password is not updated");
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
    }
}