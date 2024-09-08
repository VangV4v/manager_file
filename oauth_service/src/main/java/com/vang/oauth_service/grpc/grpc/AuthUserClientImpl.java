package com.vang.oauth_service.grpc.grpc;

import com.vang.oauth_service.grpc.generated.CreateOauth2InfoGrpc;
import com.vang.oauth_service.grpc.generated.CreateOauth2InfoReply;
import com.vang.oauth_service.grpc.generated.CreateOauth2InfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserClientImpl {

    private final CreateOauth2InfoGrpc.CreateOauth2InfoBlockingStub createOauth2InfoBlockingStub;

    @Autowired
    public AuthUserClientImpl(CreateOauth2InfoGrpc.CreateOauth2InfoBlockingStub createOauth2InfoBlockingStub) {
        this.createOauth2InfoBlockingStub = createOauth2InfoBlockingStub;
    }

    public String authenticateOauth(String username) {

        CreateOauth2InfoReply reply = createOauth2InfoBlockingStub.authenticate(CreateOauth2InfoRequest.newBuilder().setUsername(username).build());
        return reply.getJwt();
    }
}