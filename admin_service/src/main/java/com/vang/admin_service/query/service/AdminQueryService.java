package com.vang.admin_service.query.service;

import com.vang.admin_service.query.model.AdminResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminQueryService {

    ResponseEntity<List<AdminResponseModel>> getAllAdmins();

    ResponseEntity<AdminResponseModel> getAdminById(String id);
}