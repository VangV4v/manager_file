package com.vang.auth_user_service.grpc.grpc;

import com.vang.auth_user_service.grpc.generated.AuthenticateUserGrpc;
import com.vang.auth_user_service.grpc.generated.AuthenticateUserRequest;
import com.vang.auth_user_service.grpc.generated.AuthenticateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserClientGrpc {

    private final AuthenticateUserGrpc.AuthenticateUserBlockingStub userBlockingStub;

    @Autowired
    public UserClientGrpc(AuthenticateUserGrpc.AuthenticateUserBlockingStub userBlockingStub) {
        this.userBlockingStub = userBlockingStub;
    }

    public String authenticateUser(String username) {

        String result = null;
        AuthenticateUserResponse response = userBlockingStub.authenticate(AuthenticateUserRequest.newBuilder().setUsername(username).build());
        if(response.getStatus()) {

            result = response.getPassword();
        }
        return result;
    }

}