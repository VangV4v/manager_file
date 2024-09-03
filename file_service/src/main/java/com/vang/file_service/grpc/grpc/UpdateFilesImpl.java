package com.vang.file_service.grpc.grpc;

import com.vang.file_service.data.FileRepository;
import com.vang.file_service.grpc.generated.UpdateStatusFilesGrpc;
import com.vang.file_service.grpc.generated.UpdateStatusFilesReply;
import com.vang.file_service.grpc.generated.UpdateStatusFilesRequest;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UpdateFilesImpl extends UpdateStatusFilesGrpc.UpdateStatusFilesImplBase {

    private final FileRepository fileRepository;

    @Autowired
    public UpdateFilesImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    @Override
    public void updateStatus(UpdateStatusFilesRequest request, StreamObserver<UpdateStatusFilesReply> responseObserver) {

        int rowChange = fileRepository.updateAllByFolderIdAndUserId(request.getFolderId(), request.getUserId(), request.getStatus());
        UpdateStatusFilesReply reply = UpdateStatusFilesReply.newBuilder().setRowChange(rowChange).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}