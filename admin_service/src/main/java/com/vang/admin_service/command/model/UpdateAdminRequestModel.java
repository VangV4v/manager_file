package com.vang.admin_service.command.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UpdateAdminRequestModel extends AdminRequestModel {

    private String hdnOldEmail;
}