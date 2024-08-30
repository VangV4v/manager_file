package com.vang.folder_service.grpc.grpc;

import com.vang.folder_service.grpc.generated.GetUserInfoByUsernameGrpc;
import com.vang.folder_service.grpc.generated.GetUserInfoByUsernameRequest;
import com.vang.folder_service.grpc.generated.GetUserInfoByUsernameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserClientImpl {

    private final GetUserInfoByUsernameGrpc.GetUserInfoByUsernameBlockingStub userInfoByUsernameBlockingStub;

    @Autowired
    public UserClientImpl(GetUserInfoByUsernameGrpc.GetUserInfoByUsernameBlockingStub userInfoByUsernameBlockingStub) {
        this.userInfoByUsernameBlockingStub = userInfoByUsernameBlockingStub;
    }

    public String getUserInfoByUsername(String username) {

        String result = null;
        GetUserInfoByUsernameResponse response = userInfoByUsernameBlockingStub.getUserInfoByUsername(GetUserInfoByUsernameRequest.newBuilder().setUsername(username).build());
        if(response.getStatus()) {

            result = response.getUserResponse();
        }
        return result;
    }
}