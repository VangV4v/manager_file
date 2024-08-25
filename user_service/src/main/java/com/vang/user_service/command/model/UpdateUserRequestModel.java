package com.vang.user_service.command.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequestModel extends UserRequestModel {

    private String hdnOldEmail;
}