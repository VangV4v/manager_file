package com.vang.user_service.grpc.grpc;

import com.google.gson.Gson;
import com.vang.user_service.data.UserRepository;
import com.vang.user_service.data.Users;
import com.vang.user_service.grpc.generated.GetUserInfoByUsernameGrpc;
import com.vang.user_service.grpc.generated.GetUserInfoByUsernameRequest;
import com.vang.user_service.grpc.generated.GetUserInfoByUsernameResponse;
import com.vang.user_service.grpc.grpcmodel.UserGrpcModel;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class GetUserInfoByUsernameImpl extends GetUserInfoByUsernameGrpc.GetUserInfoByUsernameImplBase {

    private final UserRepository userRepository;

    @Autowired
    public GetUserInfoByUsernameImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUserInfoByUsername(GetUserInfoByUsernameRequest request, StreamObserver<GetUserInfoByUsernameResponse> responseObserver) {

        Gson gson = new Gson();
        Users users = userRepository.findByUsername(request.getUsername());
        UserGrpcModel userGrpcModel = new UserGrpcModel();
        BeanUtils.copyProperties(users, userGrpcModel);
        String userJsonData = gson.toJson(userGrpcModel);
        GetUserInfoByUsernameResponse response = GetUserInfoByUsernameResponse.newBuilder().setStatus(Boolean.TRUE).setUserResponse(userJsonData).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}