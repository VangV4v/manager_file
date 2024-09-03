package com.vang.file_service.command.event;

import com.vang.file_service.common.FileCommon;
import com.vang.file_service.data.FileRepository;
import com.vang.file_service.data.Files;
import com.vang.file_service.grpc.grpc.FolderClientImpl;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import lombok.SneakyThrows;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileEventsHandler {

    private final FolderClientImpl folderClient;
    private final FileRepository fileRepository;
    private final MinioClient minioClient;

    @Autowired
    public FileEventsHandler(FolderClientImpl folderClient, FileRepository fileRepository, MinioClient minioClient) {
        this.folderClient = folderClient;
        this.fileRepository = fileRepository;
        this.minioClient = minioClient;
    }

    @EventHandler
    public void handle(FileCreatedEvent event) {

        Files files = new Files();
        BeanUtils.copyProperties(event, files);
        fileRepository.save(files);
        //update count in folder
        folderClient.updateCountOfFile(event.getFolderId(), 1);
    }

    @EventHandler
    public void handle(FileUpdatedEvent event) {

        Files files = new Files();
        BeanUtils.copyProperties(event, files);
        fileRepository.save(files);
    }

    @SneakyThrows
    @EventHandler
    public void handle(FileDeletedEvent event) {

        fileRepository.deleteById(event.getFileId());
        //update count in folder
        folderClient.updateCountOfFile(event.getFolderId(), 2);
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(FileCommon.BUCKET)
                .object(event.getFileName()).build();
        minioClient.removeObject(removeObjectArgs);
    }
}