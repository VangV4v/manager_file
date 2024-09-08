package com.vang.oauth_service.configuration;

import com.vang.oauth_service.common.OauthCommon;
import com.vang.oauth_service.grpc.generated.CreateOauth2InfoGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {

    @Bean
    CreateOauth2InfoGrpc.CreateOauth2InfoBlockingStub createOauth2InfoBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(OauthCommon.IP, OauthCommon.GRPC_AUTH_USER_PORT).usePlaintext().build();
        return CreateOauth2InfoGrpc.newBlockingStub(channel);
    }

}