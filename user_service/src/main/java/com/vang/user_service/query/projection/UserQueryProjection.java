package com.vang.user_service.query.projection;

import com.vang.user_service.data.UserRepository;
import com.vang.user_service.data.Users;
import com.vang.user_service.query.model.UserResponseModel;
import com.vang.user_service.query.queries.FindAllUsers;
import com.vang.user_service.query.queries.FindByUserId;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserQueryProjection {

    private final UserRepository userRepository;

    @Autowired
    public UserQueryProjection(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    public UserResponseModel getUserById(FindByUserId byUserId) {

        Users users = userRepository.findById(byUserId.getUserId()).orElse(null);
        UserResponseModel responseModel = new UserResponseModel();
        if(ObjectUtils.isEmpty(users)) {

            responseModel.initDefault();
        } else {

            BeanUtils.copyProperties(users, responseModel);
        }
        return responseModel;
    }

    @QueryHandler
    public List<UserResponseModel> getAllUsers(FindAllUsers allUsers) {

        List<Users> users = userRepository.findAll();
        List<UserResponseModel> responseModels = new ArrayList<UserResponseModel>();
        users.forEach(e -> {

            UserResponseModel responseModel = new UserResponseModel();
            BeanUtils.copyProperties(e, responseModel);
            responseModels.add(responseModel);
        });
        return responseModels;
    }

}