package com.vang.auth_user_service.configuration;

import com.vang.auth_user_service.common.AuthUserCommon;
import com.vang.auth_user_service.grpc.generated.AuthenticateUserGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {

    @Bean
    public AuthenticateUserGrpc.AuthenticateUserBlockingStub userBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(AuthUserCommon.IP, AuthUserCommon.GRPC_USER_PORT).usePlaintext().build();
        return AuthenticateUserGrpc.newBlockingStub(channel);
    }

}