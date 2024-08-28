package com.vang.auth_user_service.grpc.grpc;

import com.vang.auth_user_service.common.AuthUserCommon;
import com.vang.auth_user_service.grpc.generated.GetAuthUserInformationGrpc;
import com.vang.auth_user_service.grpc.generated.GetAuthUserInformationRequest;
import com.vang.auth_user_service.grpc.generated.GetAuthUserInformationResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

@GrpcService
public class GetAuthUserInformationServer extends GetAuthUserInformationGrpc.GetAuthUserInformationImplBase {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public GetAuthUserInformationServer(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void getAuthUserInfo(GetAuthUserInformationRequest request, StreamObserver<GetAuthUserInformationResponse> responseObserver) {

        GetAuthUserInformationResponse response;
        String username = redisTemplate.opsForValue().get(AuthUserCommon.USERNAME_KEY);
        String expirationData = redisTemplate.opsForValue().get(AuthUserCommon.USERNAME_EXPIRATION_KEY);
        Date expirationDate = expirationData != null ? new Date(Long.parseLong(expirationData)) : new Date();
        if(!StringUtils.isEmpty(username) && new Date().before(expirationDate)) {

            response = GetAuthUserInformationResponse.newBuilder().setStatus(Boolean.TRUE).setUsername(username).build();
        } else {

            response = GetAuthUserInformationResponse.newBuilder().setStatus(Boolean.FALSE).build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}