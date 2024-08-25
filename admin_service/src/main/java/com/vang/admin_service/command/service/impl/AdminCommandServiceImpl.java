package com.vang.admin_service.command.service.impl;

import com.vang.admin_service.command.command.CreateAdminCommand;
import com.vang.admin_service.command.command.DeleteAdminCommand;
import com.vang.admin_service.command.command.UpdateAdminCommand;
import com.vang.admin_service.command.model.AdminRequestModel;
import com.vang.admin_service.command.model.RegisterMessageModel;
import com.vang.admin_service.command.model.ResponseModel;
import com.vang.admin_service.command.model.UpdateAdminRequestModel;
import com.vang.admin_service.command.service.AdminCommandService;
import com.vang.admin_service.common.AdminCommon;
import com.vang.admin_service.data.AdminRepository;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdminCommandServiceImpl implements AdminCommandService {

    private final CommandGateway commandGateway;
    private final AdminRepository adminRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public AdminCommandServiceImpl(CommandGateway commandGateway, AdminRepository adminRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.commandGateway = commandGateway;
        this.adminRepository = adminRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public ResponseEntity<ResponseModel> addAdmin(AdminRequestModel requestModel) {

        ResponseModel responseModel = new ResponseModel();
        CreateAdminCommand createAdminCommand = new CreateAdminCommand();
        RegisterMessageModel messageModel = new RegisterMessageModel();
        //check exist data
        long getCountOfEmail = adminRepository.countByEmail(requestModel.getEmail());
        long getCountOfUsername = adminRepository.countByUsername(requestModel.getUsername());

        if (getCountOfEmail > 0) {

            responseModel.getErrors().add(AdminCommon.ERR_EXIST_EMAIL);
        }
        if (getCountOfUsername > 0) {

            responseModel.getErrors().add(AdminCommon.ERR_EXIST_USERNAME);
        }
        if (!responseModel.getErrors().isEmpty()) {

            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
        //end check data
        //generate string id
        requestModel.setId(AdminCommon.generateStringId());
        requestModel.setCreatedDate(AdminCommon.getCurrentDate());
        requestModel.setStatus(AdminCommon.Numbers.ONE);
        requestModel.setPassword(AdminCommon.convertPassword(requestModel.getPassword()));
        BeanUtils.copyProperties(requestModel, createAdminCommand);
        //check return is String
        String responseCommand = commandGateway.sendAndWait(createAdminCommand);
        //check and return result: class java.lang.String cannot be cast to class com.vang.admin_service.command.command.CreateAdminCommand (java.lang.String is in module java.base of loader 'bootstrap';
        if(!StringUtils.isEmpty(responseCommand)) {

            responseModel.setSuccess(true);
            responseModel.setMessage(AdminCommon.SUCCESS_ADD);
            //notification
            messageModel.setEmail(requestModel.getEmail());
            messageModel.setFullName(requestModel.getFirstName()+" "+requestModel.getLastName());
            messageModel.setSubject("REGISTER ADMIN SUCCESS");
            kafkaTemplate.send("registerAccount", messageModel);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);

        } else {

            responseModel.getErrors().add(AdminCommon.ERR_SYSTEM);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    public ResponseEntity<ResponseModel> updateAdmin(UpdateAdminRequestModel requestModel) {

        ResponseModel responseModel = new ResponseModel();
        UpdateAdminCommand updateAdminCommand = new UpdateAdminCommand();
        //check data exist
        if(!requestModel.getEmail().equals(requestModel.getHdnOldEmail())) {
            long getCountByEmail = adminRepository.countByEmailAndOldEmail(requestModel.getEmail(), requestModel.getHdnOldEmail());
            if (getCountByEmail > 0) {

                responseModel.getErrors().add(AdminCommon.ERR_EXIST_EMAIL);
                return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
            }
        }
        //end check
        requestModel.setLastModified(AdminCommon.getCurrentDate());
        requestModel.setPassword(AdminCommon.convertPassword(requestModel.getPassword()));
        BeanUtils.copyProperties(requestModel, updateAdminCommand);
        String responseCommand = commandGateway.sendAndWait(updateAdminCommand);
        if(!StringUtils.isEmpty(responseCommand)) {

            responseModel.setSuccess(true);
            responseModel.setMessage(AdminCommon.SUCCESS_UPDATE);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } else {

            responseModel.getErrors().add(AdminCommon.ERR_SYSTEM);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<ResponseModel> deleteAdmin(AdminRequestModel requestModel) {

        ResponseModel responseModel = new ResponseModel();
        DeleteAdminCommand deleteAdminCommand = new DeleteAdminCommand();
        BeanUtils.copyProperties(requestModel, deleteAdminCommand);
        String responseCommand = commandGateway.sendAndWait(deleteAdminCommand);
        if(!StringUtils.isEmpty(responseCommand)) {

            responseModel.setSuccess(true);
            responseModel.setMessage(AdminCommon.SUCCESS_DELETE);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } else {

            responseModel.getErrors().add(AdminCommon.ERR_SYSTEM);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }

    }
}