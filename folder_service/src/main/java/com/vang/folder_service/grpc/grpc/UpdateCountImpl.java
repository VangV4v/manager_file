package com.vang.folder_service.grpc.grpc;

import com.vang.folder_service.data.FolderRepository;
import com.vang.folder_service.grpc.generated.UpdateCountOfFileGrpc;
import com.vang.folder_service.grpc.generated.UpdateCountOfFileReply;
import com.vang.folder_service.grpc.generated.UpdateCountOfFileRequest;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UpdateCountImpl extends UpdateCountOfFileGrpc.UpdateCountOfFileImplBase {

    private final FolderRepository folderRepository;

    @Autowired
    public UpdateCountImpl(FolderRepository folderRepository) {

        this.folderRepository = folderRepository;
    }

    @Transactional
    @Override
    public void updateCount(UpdateCountOfFileRequest request, StreamObserver<UpdateCountOfFileReply> responseObserver) {

        UpdateCountOfFileReply reply;
        int rowChange = 0;
        if(request.getType() == 1) {

            rowChange = folderRepository.updateIncreamentFileInFolder(request.getFolderId());
        } else if (request.getType() == 2) {

            rowChange = folderRepository.updateDecreamentFileInFolder(request.getFolderId());
        }
        reply = UpdateCountOfFileReply.newBuilder().setRowChange(rowChange).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}