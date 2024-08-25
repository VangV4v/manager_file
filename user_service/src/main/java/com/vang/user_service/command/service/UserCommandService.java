package com.vang.user_service.command.service;

import com.vang.user_service.command.model.ResponseModel;
import com.vang.user_service.command.model.UpdateUserRequestModel;
import com.vang.user_service.command.model.UserRequestModel;
import org.springframework.http.ResponseEntity;

public interface UserCommandService {

    ResponseEntity<ResponseModel> addUser(UserRequestModel requestModel);
    ResponseEntity<ResponseModel> updateUser(UpdateUserRequestModel requestModel);
    ResponseEntity<ResponseModel> deleteUser(UserRequestModel requestModel);
}