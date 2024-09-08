package com.vang.auth_user_service.grpc.grpc;

import com.vang.auth_user_service.common.AuthUserCommon;
import com.vang.auth_user_service.grpc.generated.CreateOauth2InfoGrpc;
import com.vang.auth_user_service.grpc.generated.CreateOauth2InfoReply;
import com.vang.auth_user_service.grpc.generated.CreateOauth2InfoRequest;
import com.vang.auth_user_service.jwt.JwtService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@GrpcService
public class CreateOAuth2impl extends CreateOauth2InfoGrpc.CreateOauth2InfoImplBase {

    private final JwtService jwtService;
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public CreateOAuth2impl(final JwtService jwtService, RedisTemplate<String, String> redisTemplate) {
        this.jwtService = jwtService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void authenticate(CreateOauth2InfoRequest request, StreamObserver<CreateOauth2InfoReply> responseObserver) {

        String jwt = jwtService.generateToken(request.getUsername());
        //save data into redis
        redisTemplate.opsForValue().set(AuthUserCommon.USERNAME_KEY, request.getUsername());
        redisTemplate.opsForValue().set(AuthUserCommon.USERNAME_EXPIRATION_KEY, System.currentTimeMillis()+ (1000 * 60 * 30)+"");
        CreateOauth2InfoReply reply = CreateOauth2InfoReply.newBuilder().setStatus(Boolean.TRUE).setJwt(jwt).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}