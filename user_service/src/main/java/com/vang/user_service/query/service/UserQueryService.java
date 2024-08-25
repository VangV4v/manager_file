package com.vang.user_service.query.service;

import com.vang.user_service.query.model.UserResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserQueryService {
    ResponseEntity<UserResponseModel> getUserById(String id);
    ResponseEntity<List<UserResponseModel>> getAllUsers();
}