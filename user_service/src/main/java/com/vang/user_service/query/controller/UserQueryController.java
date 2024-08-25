package com.vang.user_service.query.controller;

import com.vang.user_service.query.model.UserResponseModel;
import com.vang.user_service.query.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/")
public class UserQueryController {

    private final UserQueryService userQueryService;

    @Autowired
    public UserQueryController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping("{id}/")
    public ResponseEntity<UserResponseModel> getUserById(@PathVariable("id") String id) {

        return userQueryService.getUserById(id);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseModel>> getAllUsers() {

        return userQueryService.getAllUsers();
    }

}