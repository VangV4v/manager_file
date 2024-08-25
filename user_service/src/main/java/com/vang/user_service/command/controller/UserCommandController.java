package com.vang.user_service.command.controller;

import com.vang.user_service.command.model.ResponseModel;
import com.vang.user_service.command.model.UpdateUserRequestModel;
import com.vang.user_service.command.model.UserRequestModel;
import com.vang.user_service.command.service.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/")
public class UserCommandController {

    private final UserCommandService userCommandService;

    @Autowired
    public UserCommandController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> addUser(@RequestBody UserRequestModel requestModel) {

        return userCommandService.addUser(requestModel);
    }

    @PutMapping
    public ResponseEntity<ResponseModel> updateUser(@RequestBody UpdateUserRequestModel requestModel) {

        return userCommandService.updateUser(requestModel);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteUser(@RequestBody UserRequestModel requestModel) {

        return userCommandService.deleteUser(requestModel);
    }

}