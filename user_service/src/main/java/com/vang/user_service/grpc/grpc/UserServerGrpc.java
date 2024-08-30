package com.vang.user_service.grpc.grpc;

import com.vang.user_service.data.UserRepository;
import com.vang.user_service.grpc.generated.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
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
}