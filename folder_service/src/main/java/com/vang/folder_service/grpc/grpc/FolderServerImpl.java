package com.vang.folder_service.grpc.grpc;

import com.google.gson.Gson;
import com.vang.folder_service.data.FolderRepository;
import com.vang.folder_service.data.Folders;
import com.vang.folder_service.grpc.generated.FindFolderByIdGrpc;
import com.vang.folder_service.grpc.generated.FindFolderByIdReply;
import com.vang.folder_service.grpc.generated.FindFolderByIdRequest;
import com.vang.folder_service.grpc.grpcmodel.FolderGrpcModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

@GrpcService
public class FolderServerImpl extends FindFolderByIdGrpc.FindFolderByIdImplBase {

    private final FolderRepository folderRepository;

    @Autowired
    public FolderServerImpl(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @Override
    public void getData(FindFolderByIdRequest request, StreamObserver<FindFolderByIdReply> responseObserver) {

        FindFolderByIdReply reply;
        Gson gson = new Gson();
        Folders folders = folderRepository.findById(request.getFolderId()).orElse(null);
        FolderGrpcModel folderGrpcModel = new FolderGrpcModel();
        if(ObjectUtils.isEmpty(folders)) {

            reply = FindFolderByIdReply.newBuilder().setStatus(Boolean.FALSE).build();
        } else {

            BeanUtils.copyProperties(folders, folderGrpcModel);
            String convertToJson = gson.toJson(folderGrpcModel);
            reply = FindFolderByIdReply.newBuilder().setStatus(Boolean.TRUE).setResponseJson(convertToJson).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}