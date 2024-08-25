package com.vang.admin_service.query.service.impl;

import com.vang.admin_service.query.model.AdminResponseModel;
import com.vang.admin_service.query.queries.FindAdminById;
import com.vang.admin_service.query.queries.FindAllAdmins;
import com.vang.admin_service.query.service.AdminQueryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminQueryServiceImpl implements AdminQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public AdminQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public ResponseEntity<List<AdminResponseModel>> getAllAdmins() {

        FindAllAdmins allAdmins = new FindAllAdmins();
        List<AdminResponseModel> responseModels = queryGateway.query(allAdmins, ResponseTypes.multipleInstancesOf(AdminResponseModel.class)).join();
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AdminResponseModel> getAdminById(String id) {

        FindAdminById adminById = new FindAdminById(id);
        AdminResponseModel responseModel = queryGateway.query(adminById, ResponseTypes.instanceOf(AdminResponseModel.class)).join();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

}