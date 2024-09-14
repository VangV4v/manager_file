package com.vang.folder_service.command.service.impl;

import com.google.gson.Gson;
import com.vang.folder_service.command.command.CreateFolderCommand;
import com.vang.folder_service.command.command.DeleteFolderCommand;
import com.vang.folder_service.command.command.UpdateFolderCommand;
import com.vang.folder_service.command.model.FolderRequestModel;
import com.vang.folder_service.command.model.ResponseModel;
import com.vang.folder_service.command.service.FolderCommandService;
import com.vang.folder_service.command.sharedata.SharedData;
import com.vang.folder_service.common.FolderCommon;
import com.vang.folder_service.data.FolderRepository;
import com.vang.folder_service.grpc.grpc.AuthUserClient;
import com.vang.folder_service.grpc.grpc.GatewayClientImpl;
import com.vang.folder_service.grpc.grpc.UserClientImpl;
import com.vang.folder_service.grpc.grpcmodel.UserGrpcModel;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FolderCommandServiceImpl implements FolderCommandService {

    private final CommandGateway commandGateway;
    private final FolderRepository folderRepository;
    private final AuthUserClient authUserClient;
    private final GatewayClientImpl gatewayClient;
    private final UserClientImpl userClient;

    @Autowired
    public FolderCommandServiceImpl(CommandGateway commandGateway, FolderRepository folderRepository, AuthUserClient authUserClient, UserClientImpl userClient, GatewayClientImpl gatewayClient) {
        this.commandGateway = commandGateway;
        this.folderRepository = folderRepository;
        this.authUserClient = authUserClient;
        this.userClient = userClient;
        this.gatewayClient = gatewayClient;
    }

    @Override
    public ResponseEntity<ResponseModel> addFolder(FolderRequestModel requestModel) {

        Gson gson = new Gson();
        CreateFolderCommand createFolderCommand = new CreateFolderCommand();
        ResponseModel responseModel = new ResponseModel();
        //get base user data
//        String authUserInfo = authUserClient.getAuthUserInformation();
        String authUserInfo = gatewayClient.getUsernameJwt();
        String userData = userClient.getUserInfoByUsername(authUserInfo);
        UserGrpcModel userGrpcModel = gson.fromJson(userData, UserGrpcModel.class);
        //check data exist
        long checkExistFolder = folderRepository.countByFolderName(requestModel.getFolderName(), userGrpcModel.getUserId());
        if(checkExistFolder > 0) {

            requestModel.setFolderName(requestModel.getFolderName()+"_"+checkExistFolder);
        }
        //end check data exist
        requestModel.setUserId(userGrpcModel.getUserId());
        requestModel.setUserInformation(userData);
        requestModel.setFolderId(FolderCommon.generateStringId());
        requestModel.setStatus(1);
        requestModel.setCreatedDate(FolderCommon.getCurrentDate());
        BeanUtils.copyProperties(requestModel, createFolderCommand);

        String folderResponseCommand = commandGateway.sendAndWait(createFolderCommand);
        if(StringUtils.isEmpty(folderResponseCommand)) {


            responseModel.setSuccess(Boolean.FALSE);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        } else {

            responseModel.setSuccess(Boolean.TRUE);
            responseModel.setFolderData(SharedData.getInstance());
            responseModel.setMessage(FolderCommon.SUCCESS_ADD);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> updateFolder(FolderRequestModel requestModel) {

        UpdateFolderCommand updateFolderCommand = new UpdateFolderCommand();
        ResponseModel responseModel = new ResponseModel();
        //check data exist
        long checkExistFolder = folderRepository.countByFolderName(requestModel.getFolderName(), requestModel.getUserId());
        if(checkExistFolder > 0) {

            requestModel.setFolderName(requestModel.getFolderName()+"_"+checkExistFolder);
        }
        //end check data
        requestModel.setLastModified(FolderCommon.getCurrentDate());
        BeanUtils.copyProperties(requestModel, updateFolderCommand);
        String responseCommand = commandGateway.sendAndWait(updateFolderCommand);

        if(StringUtils.isEmpty(responseCommand)) {

            responseModel.setSuccess(Boolean.FALSE);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        } else {

            responseModel.setSuccess(Boolean.TRUE);
            responseModel.setMessage(FolderCommon.SUCCESS_UPDATE);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> deleteFolder(FolderRequestModel requestModel) {

        DeleteFolderCommand deleteFolderCommand = new DeleteFolderCommand();
        ResponseModel responseModel = new ResponseModel();
        BeanUtils.copyProperties(requestModel, deleteFolderCommand);
        String responseCommand = commandGateway.sendAndWait(deleteFolderCommand);

        if(StringUtils.isEmpty(responseCommand)) {

            responseModel.setSuccess(Boolean.FALSE);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        } else {

            responseModel.setSuccess(Boolean.TRUE);
            responseModel.setMessage(FolderCommon.SUCCESS_DELETE);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
    }

}