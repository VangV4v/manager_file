package com.vang.user_service.grpc.grpc;

import com.vang.user_service.data.UserRepository;
import com.vang.user_service.grpc.generated.CheckMailForgotPasswordGrpc;
import com.vang.user_service.grpc.generated.CheckMailForgotPasswordReply;
import com.vang.user_service.grpc.generated.CheckMailForgotPasswordRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class CheckMailForgotPasswordImpl extends CheckMailForgotPasswordGrpc.CheckMailForgotPasswordImplBase {

    private final UserRepository userRepository;

    @Autowired
    public CheckMailForgotPasswordImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void checkForgot(CheckMailForgotPasswordRequest request, StreamObserver<CheckMailForgotPasswordReply> responseObserver) {

        long checkCountEmail = userRepository.countByEmail(request.getEmail());
        CheckMailForgotPasswordReply reply;
        if(checkCountEmail > 0) {

            reply = CheckMailForgotPasswordReply.newBuilder().setStatus(Boolean.TRUE).build();
        } else {

            reply = CheckMailForgotPasswordReply.newBuilder().setStatus(Boolean.FALSE).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}