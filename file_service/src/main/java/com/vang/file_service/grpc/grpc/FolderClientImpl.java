package com.vang.file_service.grpc.grpc;

import com.vang.file_service.grpc.generated.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderClientImpl {

    private final FindFolderByIdGrpc.FindFolderByIdBlockingStub findFolderByIdBlockingStub;
    private final UpdateCountOfFileGrpc.UpdateCountOfFileBlockingStub updateCountOfFileBlockingStub;

    @Autowired
    public FolderClientImpl(FindFolderByIdGrpc.FindFolderByIdBlockingStub findFolderByIdBlockingStub, UpdateCountOfFileGrpc.UpdateCountOfFileBlockingStub updateCountOfFileBlockingStub) {
        this.findFolderByIdBlockingStub = findFolderByIdBlockingStub;
        this.updateCountOfFileBlockingStub = updateCountOfFileBlockingStub;
    }

    public String findFolderById(String id) {

        String result = null;
        FindFolderByIdReply reply = findFolderByIdBlockingStub.getData(FindFolderByIdRequest.newBuilder().setFolderId(id).build());
        if(reply.getStatus()) {

            result = reply.getResponseJson();
        }
        return result;
    }

    public int updateCountOfFile(String folderId, int type) {

        UpdateCountOfFileReply reply = updateCountOfFileBlockingStub.updateCount(UpdateCountOfFileRequest.newBuilder().setFolderId(folderId).setType(type).build());
        return reply.getRowChange();
    }

}