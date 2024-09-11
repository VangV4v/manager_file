package com.vang.file_service.grpc.grpc;

import com.vang.file_service.grpc.generated.GetUsernameStoreGrpc;
import com.vang.file_service.grpc.generated.GetUsernameStoreReply;
import com.vang.file_service.grpc.generated.GetUsernameStoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatewayClientImpl {

    private final GetUsernameStoreGrpc.GetUsernameStoreBlockingStub getUsernameStoreBlockingStub;

    @Autowired
    public GatewayClientImpl(GetUsernameStoreGrpc.GetUsernameStoreBlockingStub getUsernameStoreBlockingStub) {
        this.getUsernameStoreBlockingStub = getUsernameStoreBlockingStub;
    }

    public String getUsernameJwt() {

        String username = null;
        GetUsernameStoreReply reply = getUsernameStoreBlockingStub.getStoreData(GetUsernameStoreRequest.newBuilder().setType(0).build());
        if(reply.getStatus()) {

            username = reply.getUsername();
        }
        return username;
    }

}