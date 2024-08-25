package com.vang.admin_service.query.controller;

import com.vang.admin_service.query.model.AdminResponseModel;
import com.vang.admin_service.query.service.AdminQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins/")
public class AdminQueryController {

    private final AdminQueryService adminQueryService;

    @Autowired
    public AdminQueryController(AdminQueryService adminQueryService) {
        this.adminQueryService = adminQueryService;
    }

    @GetMapping
    public ResponseEntity<List<AdminResponseModel>> getAllAdmins() {

        return adminQueryService.getAllAdmins();
    }

    @GetMapping("{id}/")
    public ResponseEntity<AdminResponseModel> getAdminById(@PathVariable("id") String id) {

        return adminQueryService.getAdminById(id);
    }

}