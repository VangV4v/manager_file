package com.vang.file_service.command.service.impl;

import com.google.gson.Gson;
import com.vang.file_service.command.command.CreateFileCommand;
import com.vang.file_service.command.command.DeleteFileCommand;
import com.vang.file_service.command.command.UpdateFileCommand;
import com.vang.file_service.command.model.FileRequestModel;
import com.vang.file_service.command.model.ResponseModel;
import com.vang.file_service.command.service.FileCommandService;
import com.vang.file_service.common.FileCommon;
import com.vang.file_service.grpc.grpc.AuthUserClient;
import com.vang.file_service.grpc.grpc.FolderClientImpl;
import com.vang.file_service.grpc.grpc.GatewayClientImpl;
import com.vang.file_service.grpc.grpc.UserClientImpl;
import com.vang.file_service.grpc.grpcmodel.FolderGrpcModel;
import com.vang.file_service.grpc.grpcmodel.UserGrpcModel;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FileCommandServiceImpl implements FileCommandService {

    private final UserClientImpl userClient;
    private final FolderClientImpl folderClient;
    private final CommandGateway commandGateway;
    private final MinioClient minioClient;
    private final GatewayClientImpl gatewayClient;

    @Autowired
    public FileCommandServiceImpl(UserClientImpl userClient, FolderClientImpl folderClient, CommandGateway commandGateway, MinioClient minioClient, GatewayClientImpl gatewayClient) {
        this.userClient = userClient;
        this.folderClient = folderClient;
        this.commandGateway = commandGateway;
        this.minioClient = minioClient;
        this.gatewayClient = gatewayClient;
    }

    @SneakyThrows
    @Override
    public ResponseEntity<ResponseModel> addFile(FileRequestModel requestModel) {

        Gson gson = new Gson();
        CreateFileCommand createFileCommand = new CreateFileCommand();
        ResponseModel responseModel = new ResponseModel();
        //get base user data
//        String authUserInfo = authUserClient.getAuthUserInformation();
        String authUserInfo = gatewayClient.getUsernameJwt();
        String userData = userClient.getUserInfoByUsername(authUserInfo);
        UserGrpcModel userGrpcModel = gson.fromJson(userData, UserGrpcModel.class);
        //get folder data
        String folderData = folderClient.findFolderById(requestModel.getFolderId());
        FolderGrpcModel folderGrpcModel = new FolderGrpcModel();
        //end get data
        requestModel.setFileId(FileCommon.generateStringId());
        requestModel.setCreatedDate(FileCommon.getCurrentDate());
        requestModel.setUserId(userGrpcModel.getUserId());
        requestModel.setUserInformation(userData);
        requestModel.setFolderInformation(folderData);
        requestModel.setFileType("1");
        requestModel.setStatus(1);
        //upload file and cache data
        if (requestModel.getFileData() != null) {

            if(requestModel.getFileData().getOriginalFilename().contains(".jpg") || requestModel.getFileData().getOriginalFilename().contains(".png") || requestModel.getFileData().getOriginalFilename().contains(".gif")){

                requestModel.setFileType("1");
            } else {

                requestModel.setFileType("2");
            }
            String fileName = (System.currentTimeMillis() + "_" + requestModel.getFileData().getOriginalFilename()).replace(" ", "_");
            String urlImage = FileCommon.END_POINT + "/" + FileCommon.BUCKET + "/" + fileName;
            PutObjectArgs uploadObject = PutObjectArgs.builder()
                    .bucket(FileCommon.BUCKET)
                    .object(fileName)
                    .stream(requestModel.getFileData().getInputStream(), requestModel.getFileData().getSize(), -1)
                    .build();
            minioClient.putObject(uploadObject);
            requestModel.setFileUrl(urlImage);
            requestModel.setFileName(fileName);
        }
        BeanUtils.copyProperties(requestModel, createFileCommand);
        String responseCommand = commandGateway.sendAndWait(createFileCommand);
        if (StringUtils.isEmpty(responseCommand)) {

            responseModel.setSuccess(Boolean.FALSE);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        } else {

            responseModel.setSuccess(Boolean.TRUE);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> updateFile(FileRequestModel requestModel) {

        UpdateFileCommand updateFileCommand = new UpdateFileCommand();
        ResponseModel responseModel = new ResponseModel();
        requestModel.setLastModified(FileCommon.getCurrentDate());
        BeanUtils.copyProperties(requestModel, updateFileCommand);
        String responseCommand = commandGateway.sendAndWait(updateFileCommand);
        if(StringUtils.isEmpty(responseCommand)) {

            responseModel.setSuccess(Boolean.FALSE);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        } else {

            responseModel.setSuccess(Boolean.TRUE);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> deleteFile(FileRequestModel requestModel) {

        ResponseModel responseModel = new ResponseModel();
        DeleteFileCommand deleteFileCommand = new DeleteFileCommand();
        BeanUtils.copyProperties(requestModel, deleteFileCommand);
        if(requestModel.getStatus() != 0) {

            responseModel.setSuccess(Boolean.FALSE);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
        String responseCommand = commandGateway.sendAndWait(deleteFileCommand);
        if(StringUtils.isEmpty(responseCommand)) {

            responseModel.setSuccess(Boolean.FALSE);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        } else {

            responseModel.setSuccess(Boolean.TRUE);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
    }

}