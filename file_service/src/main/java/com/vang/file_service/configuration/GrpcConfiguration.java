package com.vang.file_service.configuration;

import com.vang.file_service.common.FileCommon;
import com.vang.file_service.grpc.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {

    @Bean
    public GetAuthUserInformationGrpc.GetAuthUserInformationBlockingStub authUserInformationBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(FileCommon.IP, FileCommon.GRPC_AUTH_USER_PORT).usePlaintext().build();
        return GetAuthUserInformationGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetUserInfoByUsernameGrpc.GetUserInfoByUsernameBlockingStub userInfoByUsernameBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(FileCommon.IP, FileCommon.GRPC_USER_PORT).usePlaintext().build();
        return GetUserInfoByUsernameGrpc.newBlockingStub(channel);
    }

    @Bean
    public FindFolderByIdGrpc.FindFolderByIdBlockingStub findFolderByIdBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(FileCommon.IP, FileCommon.GRPC_FOLDER_PORT).usePlaintext().build();
        return FindFolderByIdGrpc.newBlockingStub(channel);
    }

    @Bean
    public UpdateCountOfFileGrpc.UpdateCountOfFileBlockingStub updateCountOfFileBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(FileCommon.IP, FileCommon.GRPC_FOLDER_PORT).usePlaintext().build();
        return UpdateCountOfFileGrpc.newBlockingStub(channel);
    }

    @Bean
    public GetUsernameStoreGrpc.GetUsernameStoreBlockingStub getUsernameStoreBlockingStub() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(FileCommon.IP, FileCommon.GRPC_GATEWAY_PORT).usePlaintext().build();
        return GetUsernameStoreGrpc.newBlockingStub(channel);
    }
}