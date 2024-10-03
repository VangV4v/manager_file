package com.vang.user_service.grpc.grpc;

import com.vang.user_service.data.UserRepository;
import com.vang.user_service.grpc.generated.UpdatePasswordGrpc;
import com.vang.user_service.grpc.generated.UpdatePasswordReply;
import com.vang.user_service.grpc.generated.UpdatePasswordRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UpdatePasswordImpl extends UpdatePasswordGrpc.UpdatePasswordImplBase {

    private final UserRepository userRepository;

    @Autowired
    public UpdatePasswordImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updatePassword(UpdatePasswordRequest request, StreamObserver<UpdatePasswordReply> responseObserver) {

        UpdatePasswordReply reply;
        int rowChange = userRepository.updatePasswordByEmail(request.getEmail(), request.getPassword());
        if(rowChange > 0) {

            reply = UpdatePasswordReply.newBuilder().setStatus(Boolean.TRUE).build();
        } else {

            reply = UpdatePasswordReply.newBuilder().setStatus(Boolean.FALSE).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}