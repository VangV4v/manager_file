package com.vang.auth_admin_service.grpc.grpc;

import com.vang.auth_admin_service.grpc.generated.AuthenticateAdminGrpc;
import com.vang.auth_admin_service.grpc.generated.AuthenticateAdminRequest;
import com.vang.auth_admin_service.grpc.generated.AuthenticateAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminClientGrpc {

    private final AuthenticateAdminGrpc.AuthenticateAdminBlockingStub adminBlockingStub;

    @Autowired
    public AdminClientGrpc(AuthenticateAdminGrpc.AuthenticateAdminBlockingStub adminBlockingStub) {
        this.adminBlockingStub = adminBlockingStub;
    }

    public String authenticate(String username) {

        AuthenticateAdminResponse response = adminBlockingStub.authenticate(AuthenticateAdminRequest.newBuilder().setUsername(username).build());
        String password = null;
        if(response.getStatus()) {

            password = response.getPassword();
        }
        return password;
    }

}