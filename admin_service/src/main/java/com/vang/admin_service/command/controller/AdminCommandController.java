package com.vang.admin_service.command.controller;

import com.vang.admin_service.command.model.AdminRequestModel;
import com.vang.admin_service.command.model.ResponseModel;
import com.vang.admin_service.command.model.UpdateAdminRequestModel;
import com.vang.admin_service.command.service.AdminCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admins/")
public class AdminCommandController {

    private final AdminCommandService adminCommandService;

    @Autowired
    public AdminCommandController(AdminCommandService adminCommandService) {
        this.adminCommandService = adminCommandService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> addAdmin(@RequestBody AdminRequestModel requestModel) {

        return adminCommandService.addAdmin(requestModel);
    }

    @PutMapping
    public ResponseEntity<ResponseModel> addAdmin(@RequestBody UpdateAdminRequestModel requestModel) {

        return adminCommandService.updateAdmin(requestModel);
    }

    @DeleteMapping
    public ResponseEntity<ResponseModel> deleteAdmin(@RequestBody AdminRequestModel requestModel) {

        return adminCommandService.deleteAdmin(requestModel);
    }

}