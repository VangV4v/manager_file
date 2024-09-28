package com.vang.file_service.query.service.impl;

import com.google.gson.Gson;
import com.vang.file_service.grpc.grpc.AuthUserClient;
import com.vang.file_service.grpc.grpc.GatewayClientImpl;
import com.vang.file_service.grpc.grpc.UserClientImpl;
import com.vang.file_service.grpc.grpcmodel.UserGrpcModel;
import com.vang.file_service.query.model.FileResponseModel;
import com.vang.file_service.query.queries.FIndAllFiles;
import com.vang.file_service.query.queries.FindAllByStatus;
import com.vang.file_service.query.queries.FindByFileId;
import com.vang.file_service.query.queries.FindByUserId;
import com.vang.file_service.query.service.FileQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileQueryServiceImpl implements FileQueryService {

    private final AuthUserClient authUserClient;
    private final UserClientImpl userClient;
    private final QueryGateway queryGateway;
    private final GatewayClientImpl gatewayClient;

    @Autowired
    public FileQueryServiceImpl(AuthUserClient authUserClient, UserClientImpl userClient, QueryGateway queryGateway, GatewayClientImpl gatewayClient) {
        this.authUserClient = authUserClient;
        this.userClient = userClient;

        this.queryGateway = queryGateway;
        this.gatewayClient = gatewayClient;
    }

    @Override
    public ResponseEntity<FileResponseModel> findByFileId(String fileId) {

        FindByFileId byFileId = new FindByFileId(fileId);
        FileResponseModel responseModel = queryGateway.query(byFileId, ResponseTypes.instanceOf(FileResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FileResponseModel>> findAllFiles() {

        FIndAllFiles allFiles = new FIndAllFiles();
        List<FileResponseModel> responseModels = queryGateway.query(allFiles, ResponseTypes.multipleInstancesOf(FileResponseModel.class)).join();
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FileResponseModel>> findByUserId(String folderId) {

        Gson gson = new Gson();
        //get base user data
//        String authUserInfo = authUserClient.getAuthUserInformation();
//        String userData = userClient.getUserInfoByUsername(authUserInfo);
        String authUserInfo = gatewayClient.getUsernameJwt();
        String userData = userClient.getUserInfoByUsername(authUserInfo);
        UserGrpcModel userGrpcModel = gson.fromJson(userData, UserGrpcModel.class);
        FindByUserId findByUserId = new FindByUserId(userGrpcModel.getUserId(), folderId);
        List<FileResponseModel> responseModels = queryGateway.query(findByUserId, ResponseTypes.multipleInstancesOf(FileResponseModel.class)).join();
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FileResponseModel>> findAllByStatusDelete() {

        Gson gson = new Gson();
        String authUserInfo = gatewayClient.getUsernameJwt();
        String userData = userClient.getUserInfoByUsername(authUserInfo);
        UserGrpcModel userGrpcModel = gson.fromJson(userData, UserGrpcModel.class);
        FindAllByStatus allByStatus = new FindAllByStatus(userGrpcModel.getUserId());
        List<FileResponseModel> responseModels = queryGateway.query(allByStatus, ResponseTypes.multipleInstancesOf(FileResponseModel.class)).join();
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }
}