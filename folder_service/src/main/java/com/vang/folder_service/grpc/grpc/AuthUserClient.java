package com.vang.folder_service.grpc.grpc;

import com.vang.folder_service.grpc.generated.GetAuthUserInformationGrpc;
import com.vang.folder_service.grpc.generated.GetAuthUserInformationRequest;
import com.vang.folder_service.grpc.generated.GetAuthUserInformationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserClient {

    private final GetAuthUserInformationGrpc.GetAuthUserInformationBlockingStub authUserInformationBlockingStub;

    @Autowired
    public AuthUserClient(GetAuthUserInformationGrpc.GetAuthUserInformationBlockingStub authUserInformationBlockingStub) {

        this.authUserInformationBlockingStub = authUserInformationBlockingStub;
    }

    public String getAuthUserInformation() {

        GetAuthUserInformationResponse response = authUserInformationBlockingStub.getAuthUserInfo(GetAuthUserInformationRequest.newBuilder().setType(0).build());
        String username = null;
        if(response.getStatus()) {

            username = response.getUsername();
        }
        return username;
    }
}