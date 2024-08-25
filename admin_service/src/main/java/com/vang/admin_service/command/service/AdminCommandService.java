package com.vang.admin_service.command.service;

import com.vang.admin_service.command.model.AdminRequestModel;
import com.vang.admin_service.command.model.ResponseModel;
import com.vang.admin_service.command.model.UpdateAdminRequestModel;
import org.springframework.http.ResponseEntity;

public interface AdminCommandService {

    ResponseEntity<ResponseModel> addAdmin(AdminRequestModel requestModel);

    ResponseEntity<ResponseModel> updateAdmin(UpdateAdminRequestModel requestModel);

    ResponseEntity<ResponseModel> deleteAdmin(AdminRequestModel requestModel);
}