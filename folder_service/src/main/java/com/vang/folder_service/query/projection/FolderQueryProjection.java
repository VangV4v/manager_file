package com.vang.folder_service.query.projection;

import com.vang.folder_service.data.FolderRepository;
import com.vang.folder_service.data.Folders;
import com.vang.folder_service.query.model.FolderResponseModel;
import com.vang.folder_service.query.queries.FindAllFolders;
import com.vang.folder_service.query.queries.FindByFolderId;
import com.vang.folder_service.query.queries.FindByUserId;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class FolderQueryProjection {

    private final FolderRepository folderRepository;

    @Autowired
    public FolderQueryProjection(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @QueryHandler
    public List<FolderResponseModel> findByUserId(FindByUserId findByUserId) {

        List<Folders> folders = folderRepository.findByUserId(findByUserId.getUserId());
        List<FolderResponseModel> folderResponseModels = new ArrayList<FolderResponseModel>();
        folders.forEach(e -> {

            FolderResponseModel folderResponseModel = new FolderResponseModel();
            BeanUtils.copyProperties(e, folderResponseModel);
            folderResponseModels.add(folderResponseModel);
        });
        return folderResponseModels;
    }

    @QueryHandler
    public FolderResponseModel findByFolderId(FindByFolderId findByFolderId) {

        Folders folders = folderRepository.findById(findByFolderId.getFolderId()).orElse(null);
        FolderResponseModel folderResponseModel = new FolderResponseModel();
        if(ObjectUtils.isEmpty(folders)) {

           folderResponseModel.initialize();
        } else {

            BeanUtils.copyProperties(folders, folderResponseModel);
        }
        return folderResponseModel;
    }

    @QueryHandler
    public List<FolderResponseModel> findAll(FindAllFolders allFolders) {

        List<Folders> folders = folderRepository.findAll();
        List<FolderResponseModel> folderResponseModels = new ArrayList<FolderResponseModel>();
        folders.forEach(e -> {

            FolderResponseModel folderResponseModel = new FolderResponseModel();
            BeanUtils.copyProperties(e, folderResponseModel);
            folderResponseModels.add(folderResponseModel);
        });
        return folderResponseModels;
    }
}