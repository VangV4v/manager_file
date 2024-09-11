package com.vang.auth_user_service.grpc.grpc;

import com.vang.auth_user_service.grpc.generated.CreateOauth2InfoGrpc;
import com.vang.auth_user_service.grpc.generated.CreateOauth2InfoReply;
import com.vang.auth_user_service.grpc.generated.CreateOauth2InfoRequest;
import com.vang.auth_user_service.jwt.JwtService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class CreateOAuth2impl extends CreateOauth2InfoGrpc.CreateOauth2InfoImplBase {

    private final JwtService jwtService;

    @Autowired
    public CreateOAuth2impl(final JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void authenticate(CreateOauth2InfoRequest request, StreamObserver<CreateOauth2InfoReply> responseObserver) {

        String jwt = jwtService.generateToken(request.getUsername());
        CreateOauth2InfoReply reply = CreateOauth2InfoReply.newBuilder().setStatus(Boolean.TRUE).setJwt(jwt).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}