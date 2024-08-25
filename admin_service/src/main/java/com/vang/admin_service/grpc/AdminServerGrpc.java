package com.vang.admin_service.grpc;

import com.vang.admin_service.data.AdminRepository;
import com.vang.admin_service.grpc.generated.AuthenticateAdminGrpc;
import com.vang.admin_service.grpc.generated.AuthenticateAdminRequest;
import com.vang.admin_service.grpc.generated.AuthenticateAdminResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class AdminServerGrpc extends AuthenticateAdminGrpc.AuthenticateAdminImplBase {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServerGrpc(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void authenticate(AuthenticateAdminRequest request, StreamObserver<AuthenticateAdminResponse> responseObserver) {

        String password = adminRepository.getPasswordByUsername(request.getUsername());
        AuthenticateAdminResponse response;
        if(!StringUtils.isEmpty(password)) {

            response = AuthenticateAdminResponse.newBuilder().setStatus(Boolean.TRUE).setPassword(password).build();
        } else {

            response = AuthenticateAdminResponse.newBuilder().setStatus(Boolean.FALSE).build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}