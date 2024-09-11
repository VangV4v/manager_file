package com.vang.api_gateway.grpc.grpc;

import com.vang.api_gateway.grpc.generated.GetUsernameStoreGrpc;
import com.vang.api_gateway.grpc.generated.GetUsernameStoreReply;
import com.vang.api_gateway.grpc.generated.GetUsernameStoreRequest;
import com.vang.api_gateway.sahred.StoreData;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;

@GrpcService
public class GatewayServerImpl extends GetUsernameStoreGrpc.GetUsernameStoreImplBase {

    @Override
    public void getStoreData(GetUsernameStoreRequest request, StreamObserver<GetUsernameStoreReply> responseObserver) {

        GetUsernameStoreReply reply;
        if(StringUtils.isEmpty(StoreData.getInstance())) {

            reply = GetUsernameStoreReply.newBuilder().setStatus(Boolean.TRUE).build();
        } else {

            reply = GetUsernameStoreReply.newBuilder().setUsername(StoreData.getInstance()).setStatus(Boolean.TRUE).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}