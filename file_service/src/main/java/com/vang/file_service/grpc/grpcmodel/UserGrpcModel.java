package com.vang.file_service.grpc.grpcmodel;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserGrpcModel implements Serializable {

    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private int type;
    private int status;
}