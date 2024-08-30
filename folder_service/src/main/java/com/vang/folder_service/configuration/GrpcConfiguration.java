package com.vang.folder_service.configuration;

import com.vang.folder_service.common.FolderCommon;
import com.vang.folder_service.grpc.generated.GetAuthUserInformationGrpc;
import com.vang.folder_service.grpc.generated.GetUserInfoByUsernameGrpc;
import com.vang.folder_service.grpc.generated.UserClientGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {

    @Bean
    public GetAuthUserInformationGrpc.GetAuthUserInformationBlockingStub authUserInformationBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(FolderCommon.IP, FolderCommon.GRPC_AUTH_USER_PORT).usePlaintext().build();
        return GetAuthUserInformationGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetUserInfoByUsernameGrpc.GetUserInfoByUsernameBlockingStub userInfoByUsernameBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(FolderCommon.IP, FolderCommon.GRPC_USER_PORT).usePlaintext().build();
        return GetUserInfoByUsernameGrpc.newBlockingStub(channel);
    }

}