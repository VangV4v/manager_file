package com.vang.folder_service.command.event;

import com.vang.folder_service.data.FolderRepository;
import com.vang.folder_service.data.Folders;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FolderEventsHandler {

    private final FolderRepository folderRepository;

    @Autowired
    public FolderEventsHandler(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @EventHandler
    public void handle(FolderCreatedEvent event) {

        Folders folders = new Folders();
        BeanUtils.copyProperties(event, folders);
        folderRepository.save(folders);
    }
}