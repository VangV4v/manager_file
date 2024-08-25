package com.vang.admin_service.query.projection;

import com.vang.admin_service.data.AdminRepository;
import com.vang.admin_service.data.Admins;
import com.vang.admin_service.query.model.AdminResponseModel;
import com.vang.admin_service.query.queries.FindAdminById;
import com.vang.admin_service.query.queries.FindAllAdmins;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminQueryProjection {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminQueryProjection(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @QueryHandler
    public List<AdminResponseModel> getAllAdmins(FindAllAdmins allAdmins) {

        List<Admins> admins = adminRepository.findAll();
        List<AdminResponseModel> models = new ArrayList<AdminResponseModel>();
        admins.forEach(e -> {
            AdminResponseModel model = new AdminResponseModel();
            BeanUtils.copyProperties(e, model);
            models.add(model);
        });
        return models;
    }

    @QueryHandler
    public AdminResponseModel getAdminById(FindAdminById adminById) {

        Admins admin = adminRepository.findById(adminById.getId()).orElse(null);
        AdminResponseModel model = new AdminResponseModel();
        if(ObjectUtils.isEmpty(admin)) {

            model.initEmptyObject();
        } else {

            BeanUtils.copyProperties(admin, model);
        }
        return model;
    }

}