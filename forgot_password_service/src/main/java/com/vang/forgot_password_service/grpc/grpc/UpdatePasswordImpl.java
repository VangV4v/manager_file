package com.vang.forgot_password_service.grpc.grpc;

import com.vang.forgot_password_service.grpc.generated.UpdatePasswordGrpc;
import com.vang.forgot_password_service.grpc.generated.UpdatePasswordReply;
import com.vang.forgot_password_service.grpc.generated.UpdatePasswordRequest;
import com.vang.forgot_password_service.model.UpdatePasswordRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdatePasswordImpl {

    private final UpdatePasswordGrpc.UpdatePasswordBlockingStub updatePasswordBlockingStub;

    @Autowired
    public UpdatePasswordImpl(UpdatePasswordGrpc.UpdatePasswordBlockingStub updatePasswordBlockingStub) {
        this.updatePasswordBlockingStub = updatePasswordBlockingStub;
    }

    public boolean updatePassword(UpdatePasswordRequestModel requestModel) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        requestModel.setPassword(passwordEncoder.encode(requestModel.getPassword()));
        UpdatePasswordReply reply = updatePasswordBlockingStub.updatePassword(UpdatePasswordRequest.newBuilder().setEmail(requestModel.getEmail()).setPassword(requestModel.getPassword()).build());
        return reply.getStatus();
    }
}