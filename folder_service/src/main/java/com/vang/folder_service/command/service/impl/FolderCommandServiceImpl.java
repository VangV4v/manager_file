package com.vang.folder_service.command.service.impl;

import com.vang.folder_service.command.command.CreateFolderCommand;
import com.vang.folder_service.command.model.FolderRequestModel;
import com.vang.folder_service.command.model.ResponseModel;
import com.vang.folder_service.command.service.FolderCommandService;
import com.vang.folder_service.data.FolderRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FolderCommandServiceImpl implements FolderCommandService {

    private final CommandGateway commandGateway;
    private final FolderRepository folderRepository;

    @Autowired
    public FolderCommandServiceImpl(CommandGateway commandGateway, FolderRepository folderRepository) {
        this.commandGateway = commandGateway;
        this.folderRepository = folderRepository;
    }

    @Override
    public ResponseEntity<ResponseModel> addFolder(FolderRequestModel requestModel) {

        CreateFolderCommand createFolderCommand = new CreateFolderCommand();
        ResponseModel responseModel = new ResponseModel();
        //check data exist
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> updateFolder(FolderRequestModel requestModel) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel> deleteFolder(FolderRequestModel requestModel) {
        return null;
    }

}