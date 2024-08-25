package com.vang.user_service.query.service.impl;

import com.vang.user_service.query.model.UserResponseModel;
import com.vang.user_service.query.queries.FindAllUsers;
import com.vang.user_service.query.queries.FindByUserId;
import com.vang.user_service.query.service.UserQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public UserQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<UserResponseModel> getUserById(String id) {

        FindByUserId byUserId = new FindByUserId(id);
        UserResponseModel responseModel = queryGateway.query(byUserId, ResponseTypes.instanceOf(UserResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserResponseModel>> getAllUsers() {

        FindAllUsers allUsers = new FindAllUsers();
        List<UserResponseModel> responseModels = queryGateway.query(allUsers, ResponseTypes.multipleInstancesOf(UserResponseModel.class)).join();
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }

}