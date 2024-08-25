package com.vang.auth_admin_service.configuration;

import com.vang.auth_admin_service.common.AuthAdminCommon;
import com.vang.auth_admin_service.grpc.generated.AuthenticateAdminGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {

    @Bean
    public AuthenticateAdminGrpc.AuthenticateAdminBlockingStub adminBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(AuthAdminCommon.IP, AuthAdminCommon.PORT).usePlaintext().build();
        return AuthenticateAdminGrpc.newBlockingStub(channel);
    }

}