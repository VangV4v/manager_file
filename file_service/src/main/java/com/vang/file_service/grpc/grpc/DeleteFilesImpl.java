package com.vang.file_service.grpc.grpc;

import com.google.gson.Gson;
import com.vang.file_service.data.FileRepository;
import com.vang.file_service.grpc.generated.DeleteFilesByFolderIdGrpc;
import com.vang.file_service.grpc.generated.DeleteFilesByFolderIdReply;
import com.vang.file_service.grpc.generated.DeleteFilesByFolderIdRequest;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class DeleteFilesImpl extends DeleteFilesByFolderIdGrpc.DeleteFilesByFolderIdImplBase {

    private final FileRepository fileRepository;

    @Autowired
    public DeleteFilesImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    @Override
    public void deleteFiles(DeleteFilesByFolderIdRequest request, StreamObserver<DeleteFilesByFolderIdReply> responseObserver) {

        int rowChange = fileRepository.deleteAllByFolderIdAndUserId(request.getFolderId(), request.getUserId());
        DeleteFilesByFolderIdReply reply = DeleteFilesByFolderIdReply.newBuilder().setRowChange(rowChange).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}