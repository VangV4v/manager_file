package com.vang.api_gateway.configuration;

import java.util.Arrays;

public class AccessRole {

    public static String[] nonAccessRoles() {

        StringBuilder nonAccessRoles = new StringBuilder();
        nonAccessRoles.append("/api/v1/public-store/,");
        nonAccessRoles.append("/api/v1/auth/admin/,");
        return Arrays.stream(nonAccessRoles.toString().split(",")).toArray(String[]::new);
    };

    public static String[] accessAdminRole() {

        StringBuilder adminRoles = new StringBuilder();
        adminRoles.append("/api/v1/admins/,");
        return Arrays.stream(adminRoles.toString().split(",")).toArray(String[]::new);
    }
}
