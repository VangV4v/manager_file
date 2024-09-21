package com.vang.folder_service.command.event;

import com.vang.folder_service.command.model.FolderResponseModel;
import com.vang.folder_service.command.sharedata.SharedData;
import com.vang.folder_service.data.FolderRepository;
import com.vang.folder_service.data.Folders;
import com.vang.folder_service.grpc.grpc.FileClientImpl;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FolderEventsHandler {

    private final FolderRepository folderRepository;
    private final FileClientImpl fileClient;

    @Autowired
    public FolderEventsHandler(FolderRepository folderRepository, FileClientImpl fileClient) {
        this.folderRepository = folderRepository;
        this.fileClient = fileClient;
    }

    @EventHandler
    public void handle(FolderCreatedEvent event) {

        Folders folders = new Folders();
        BeanUtils.copyProperties(event, folders);
        folderRepository.save(folders);
    }

    @EventHandler
    public void handle(FolderUpdatedEvent event) {

        Folders folders = new Folders();
        BeanUtils.copyProperties(event, folders);
        folderRepository.save(folders);
        if(event.getStatus() == 0) {

            fileClient.updateStatusFiles(event.getFolderId(), event.getUserId());
        }
    }

    @EventHandler
    public void handle(FolderDeletedEvent event) {

        folderRepository.deleteById(event.getFolderId());
        fileClient.deleteFilesByFolderId(event.getFolderId(), event.getUserId());
    }

}