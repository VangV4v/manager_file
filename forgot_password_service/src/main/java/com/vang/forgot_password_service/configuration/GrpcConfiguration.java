package com.vang.forgot_password_service.configuration;

import com.vang.forgot_password_service.common.ForgotServiceCommon;
import com.vang.forgot_password_service.grpc.generated.CheckMailForgotPasswordGrpc;
import com.vang.forgot_password_service.grpc.generated.UpdatePasswordGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {

    @Bean
    CheckMailForgotPasswordGrpc.CheckMailForgotPasswordBlockingStub checkMailForgotPasswordBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(ForgotServiceCommon.GRPC_USER_HOST, ForgotServiceCommon.GRPC_USER_PORT).usePlaintext().build();
        return CheckMailForgotPasswordGrpc.newBlockingStub(channel);
    }

    @Bean
    UpdatePasswordGrpc.UpdatePasswordBlockingStub updatePasswordBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(ForgotServiceCommon.GRPC_USER_HOST, ForgotServiceCommon.GRPC_USER_PORT).usePlaintext().build();
        return UpdatePasswordGrpc.newBlockingStub(channel);
    }
}