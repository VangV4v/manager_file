package com.vang.folder_service.grpc.grpc;

import com.vang.folder_service.grpc.generated.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileClientImpl {

    private final UpdateStatusFilesGrpc.UpdateStatusFilesBlockingStub updateStatusFilesBlockingStub;
    private final DeleteFilesByFolderIdGrpc.DeleteFilesByFolderIdBlockingStub deleteFilesByFolderIdBlockingStub;

    @Autowired
    public FileClientImpl(UpdateStatusFilesGrpc.UpdateStatusFilesBlockingStub updateStatusFilesBlockingStub, DeleteFilesByFolderIdGrpc.DeleteFilesByFolderIdBlockingStub deleteFilesByFolderIdBlockingStub) {
        this.updateStatusFilesBlockingStub = updateStatusFilesBlockingStub;
        this.deleteFilesByFolderIdBlockingStub = deleteFilesByFolderIdBlockingStub;
    }

    public int updateStatusFiles(String folderId, String userId) {

        UpdateStatusFilesReply reply = updateStatusFilesBlockingStub.updateStatus(UpdateStatusFilesRequest.newBuilder().setFolderId(folderId).setUserId(userId).setStatus(0).build());
        return reply.getRowChange();
    }

    public int deleteFilesByFolderId(String folderId, String userId) {

        DeleteFilesByFolderIdReply reply = deleteFilesByFolderIdBlockingStub.deleteFiles(DeleteFilesByFolderIdRequest.newBuilder().setFolderId(folderId).setUserId(userId).build());
        return reply.getRowChange();
    }
}