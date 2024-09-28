package com.vang.file_service.query.projection;

import com.vang.file_service.data.FileRepository;
import com.vang.file_service.data.Files;
import com.vang.file_service.query.model.FileResponseModel;
import com.vang.file_service.query.queries.FIndAllFiles;
import com.vang.file_service.query.queries.FindAllByStatus;
import com.vang.file_service.query.queries.FindByFileId;
import com.vang.file_service.query.queries.FindByUserId;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileQueryProjection {

    private final FileRepository fileRepository;

    @Autowired
    public FileQueryProjection(FileRepository fileRepository) {

        this.fileRepository = fileRepository;
    }

    @QueryHandler
    public FileResponseModel findByFileId(FindByFileId byFileId) {

        Files files = fileRepository.findById(byFileId.getFileId()).orElse(null);
        FileResponseModel fileResponseModel = new FileResponseModel();
        if(ObjectUtils.isEmpty(files)) {

            fileResponseModel.initialize();
        } else {

            BeanUtils.copyProperties(files, fileResponseModel);
        }
        return fileResponseModel;
    }

    @QueryHandler
    public List<FileResponseModel> findByUserId(FindByUserId byUserId) {

        List<Files> files = fileRepository.findByUserId(byUserId.getUserId(), byUserId.getFolderId());
        List<FileResponseModel> fileResponseModels = new ArrayList<>();
        files.forEach(e -> {
            FileResponseModel fileResponseModel = new FileResponseModel();
            BeanUtils.copyProperties(e, fileResponseModel);
            fileResponseModels.add(fileResponseModel);
        });
        return fileResponseModels;
    }

    @QueryHandler
    public List<FileResponseModel> findAllFiles(FIndAllFiles allFiles) {

        List<Files> files = fileRepository.findAll();
        List<FileResponseModel> fileResponseModels = new ArrayList<>();
        files.forEach(e -> {
            FileResponseModel fileResponseModel = new FileResponseModel();
            BeanUtils.copyProperties(e, fileResponseModel);
            fileResponseModels.add(fileResponseModel);
        });
        return fileResponseModels;
    }

    @QueryHandler
    public List<FileResponseModel> findAllByStatusDelete(FindAllByStatus allByStatus) {

        List<Files> files = fileRepository.findAllByDeleteStatusAndUserId(allByStatus.getUserId());
        List<FileResponseModel> fileResponseModels = new ArrayList<>();
        files.forEach(e -> {
            FileResponseModel fileResponseModel = new FileResponseModel();
            BeanUtils.copyProperties(e, fileResponseModel);
            fileResponseModels.add(fileResponseModel);
        });
        return fileResponseModels;
    }
}