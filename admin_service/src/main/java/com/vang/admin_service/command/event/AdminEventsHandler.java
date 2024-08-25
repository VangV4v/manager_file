package com.vang.admin_service.command.event;

import com.vang.admin_service.data.AdminRepository;
import com.vang.admin_service.data.Admins;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminEventsHandler {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminEventsHandler(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @EventHandler
    public void handle(AdminCreatedEvent event) {

        Admins admins = new Admins();
        BeanUtils.copyProperties(event, admins);
        adminRepository.save(admins);
        event.setSuccess(true);
    }

    @EventHandler
    public void handle(AdminUpdatedEvent event) {

        Admins admins = new Admins();
        BeanUtils.copyProperties(event, admins);
        adminRepository.save(admins);
        event.setSuccess(true);
    }

    @EventHandler
    public void handle(AdminDeletedEvent event) {

        adminRepository.deleteById(event.getId());
        event.setSuccess(true);
    }
}