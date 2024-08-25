package com.vang.user_service.command.service.impl;

import com.vang.user_service.command.command.CreateUserCommand;
import com.vang.user_service.command.command.DeleteUserCommand;
import com.vang.user_service.command.command.UpdateUserCommand;
import com.vang.user_service.command.model.RegisterMessageModel;
import com.vang.user_service.command.model.ResponseModel;
import com.vang.user_service.command.model.UpdateUserRequestModel;
import com.vang.user_service.command.model.UserRequestModel;
import com.vang.user_service.command.service.UserCommandService;
import com.vang.user_service.common.UserCommon;
import com.vang.user_service.data.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final CommandGateway commandGateway;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public UserCommandServiceImpl(UserRepository userRepository, CommandGateway commandGateway, KafkaTemplate<String, Object> kafkaTemplate) {
        this.userRepository = userRepository;
        this.commandGateway = commandGateway;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public ResponseEntity<ResponseModel> addUser(UserRequestModel requestModel) {

        ResponseModel responseModel = new ResponseModel();
        CreateUserCommand createUserCommand = new CreateUserCommand();
        RegisterMessageModel messageModel = new RegisterMessageModel();
        //check exist data
        long countUsername = userRepository.countByUsername(requestModel.getUsername());
        long countEmail = userRepository.countByEmail(requestModel.getEmail());

        if (countUsername > 0) {

            responseModel.getErrors().add(UserCommon.ERR_USERNAME);
        }
        if (countEmail > 0) {

            responseModel.getErrors().add(UserCommon.ERR_EMAIL);
        }
        if (!responseModel.getErrors().isEmpty()) {

            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
        //end check exist
        requestModel.setUserId(UserCommon.generateStringId());
        requestModel.setType(UserCommon.Numbers.ZERO);
        requestModel.setTotalFile(UserCommon.Numbers.ZERO);
        requestModel.setTotalFolder(UserCommon.Numbers.ZERO);
        requestModel.setPassword(UserCommon.convertPassword(requestModel.getPassword()));
        requestModel.setCreatedDate(UserCommon.getCurrentDate());
        BeanUtils.copyProperties(requestModel, createUserCommand);

        String responseOfCommand = commandGateway.sendAndWait(createUserCommand);
        if (StringUtils.isEmpty(responseOfCommand)) {

            responseModel.getErrors().add(UserCommon.ERR_SYSTEM);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);

        } else {

            //send notification
            messageModel.setEmail(requestModel.getEmail());
            messageModel.setFullName(requestModel.getFirstName()+" "+requestModel.getLastName());
            messageModel.setSubject(UserCommon.SUBJECT);
            kafkaTemplate.send(UserCommon.REGISTER_TOPIC, messageModel);
            //end send notification
            responseModel.setSuccess(Boolean.TRUE);
            responseModel.setMessage(UserCommon.SUCCESS_ADD);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);

        }
    }

    @Override
    public ResponseEntity<ResponseModel> updateUser(UpdateUserRequestModel requestModel) {

        ResponseModel responseModel = new ResponseModel();
        UpdateUserCommand updateUserCommand = new UpdateUserCommand();
        //check exist data
        if (!requestModel.getEmail().equals(requestModel.getHdnOldEmail())) {

            long countEmail = userRepository.countByEmail(requestModel.getEmail());
            if (countEmail > 0) {

                responseModel.getErrors().add(UserCommon.ERR_EMAIL);
            }
        }
        //end check exist data
        requestModel.setLastModified(UserCommon.getCurrentDate());
        BeanUtils.copyProperties(requestModel, updateUserCommand);
        String responseOfCommand = commandGateway.sendAndWait(updateUserCommand);
        if (StringUtils.isEmpty(responseOfCommand)) {

            responseModel.getErrors().add(UserCommon.ERR_SYSTEM);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        } else {

            responseModel.setSuccess(Boolean.TRUE);
            responseModel.setMessage(UserCommon.SUCCESS_UPDATE);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> deleteUser(UserRequestModel requestModel) {

        ResponseModel responseModel = new ResponseModel();
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand();
        BeanUtils.copyProperties(requestModel, deleteUserCommand);
        String responseOfCommand = commandGateway.sendAndWait(deleteUserCommand);
        if (StringUtils.isEmpty(responseOfCommand)) {

            responseModel.getErrors().add(UserCommon.ERR_SYSTEM);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        } else {

            responseModel.setSuccess(Boolean.TRUE);
            responseModel.setMessage(UserCommon.SUCCESS_DELETE);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }

    }
}