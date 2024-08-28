package com.vang.user_service.grpc.grpc;

import com.google.gson.Gson;
import com.vang.user_service.data.UserRepository;
import com.vang.user_service.data.Users;
import com.vang.user_service.grpc.generated.*;
import com.vang.user_service.grpc.grpcmodel.UserGrpcModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UserServerGrpc extends AuthenticateUserGrpc.AuthenticateUserImplBase {

    private final UserRepository userRepository;

    @Autowired
    public UserServerGrpc(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void authenticate(AuthenticateUserRequest request, StreamObserver<AuthenticateUserResponse> responseObserver) {

        AuthenticateUserResponse response;
        String password = userRepository.getPasswordByUsername(request.getUsername());
        if(StringUtils.isEmpty(password)) {

            response = AuthenticateUserResponse.newBuilder().setStatus(Boolean.FALSE).build();
        } else {

            response = AuthenticateUserResponse.newBuilder().setPassword(password).setStatus(Boolean.TRUE).build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUserInfoByUsername(GetUserInfoByUsernameRequest request, StreamObserver<GetUserInfoByUsernameResponse> responseObserver) {

        Gson gson = new Gson();
        Users users = userRepository.findByUsername(request.getUsername());
        UserGrpcModel userGrpcModel = new UserGrpcModel();
        BeanUtils.copyProperties(users, userGrpcModel);
        String userJsonData = gson.toJson(userGrpcModel);
        GetUserInfoByUsernameResponse response = GetUserInfoByUsernameResponse.newBuilder().setStatus(Boolean.TRUE).setUserResponse(userJsonData).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}