package com.vang.forgot_password_service.grpc.grpc;

import com.vang.forgot_password_service.grpc.generated.CheckMailForgotPasswordGrpc;
import com.vang.forgot_password_service.grpc.generated.CheckMailForgotPasswordReply;
import com.vang.forgot_password_service.grpc.generated.CheckMailForgotPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckEmailForgotPasswordImpl {

    private final CheckMailForgotPasswordGrpc.CheckMailForgotPasswordBlockingStub checkMailForgotPasswordBlockingStub;

    @Autowired
    public CheckEmailForgotPasswordImpl(CheckMailForgotPasswordGrpc.CheckMailForgotPasswordBlockingStub checkMailForgotPasswordBlockingStub) {
        this.checkMailForgotPasswordBlockingStub = checkMailForgotPasswordBlockingStub;
    }

    public boolean checkExistEmail(String email) {

        boolean result = false;
        CheckMailForgotPasswordReply reply = checkMailForgotPasswordBlockingStub.checkForgot(CheckMailForgotPasswordRequest.newBuilder().setEmail(email).build());
        result = reply.getStatus();
        return result;
    }

}