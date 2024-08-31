package com.vang.folder_service.query.service.impl;

import com.google.gson.Gson;
import com.vang.folder_service.grpc.grpc.AuthUserClient;
import com.vang.folder_service.grpc.grpc.UserClientImpl;
import com.vang.folder_service.grpc.grpcmodel.UserGrpcModel;
import com.vang.folder_service.query.model.FolderResponseModel;
import com.vang.folder_service.query.queries.FindAllFolders;
import com.vang.folder_service.query.queries.FindByFolderId;
import com.vang.folder_service.query.queries.FindByUserId;
import com.vang.folder_service.query.service.FolderQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderQueryServiceImpl implements FolderQueryService {

    private final QueryGateway queryGateway;
    private final AuthUserClient authUserClient;
    private final UserClientImpl userClient;

    @Autowired
    public FolderQueryServiceImpl(QueryGateway queryGateway, AuthUserClient authUserClient, UserClientImpl userClient) {
        this.queryGateway = queryGateway;
        this.authUserClient = authUserClient;
        this.userClient = userClient;
    }

    @Override
    public ResponseEntity<List<FolderResponseModel>> findByUserId() {

        Gson gson = new Gson();
        String authUserInfo = authUserClient.getAuthUserInformation();
        String userData = userClient.getUserInfoByUsername(authUserInfo);
        UserGrpcModel userGrpcModel = gson.fromJson(userData, UserGrpcModel.class);
        FindByUserId byUserId = new FindByUserId(userGrpcModel.getUserId());
        List<FolderResponseModel> responseModel = queryGateway.query(byUserId, ResponseTypes.multipleInstancesOf(FolderResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FolderResponseModel> findByFolderId(String folderId) {

        FindByFolderId byFolderId = new FindByFolderId(folderId);
        FolderResponseModel responseModel = queryGateway.query(byFolderId, ResponseTypes.instanceOf(FolderResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FolderResponseModel>> findAllFolders() {

        FindAllFolders allFolders = new FindAllFolders();
        List<FolderResponseModel> responseModels = queryGateway.query(allFolders, ResponseTypes.multipleInstancesOf(FolderResponseModel.class)).join();
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }

}