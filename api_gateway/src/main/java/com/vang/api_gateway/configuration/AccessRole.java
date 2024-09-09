package com.vang.api_gateway.configuration;

import java.util.Arrays;

public class AccessRole {

    public static String[] nonAccessRoles() {

        StringBuilder nonAccessRoles = new StringBuilder();
        nonAccessRoles.append("/api/v1/public-store/,");
        nonAccessRoles.append("/api/v1/auth/admin/,");
        nonAccessRoles.append("/api/v1/auth/user/,");
        nonAccessRoles.append("/oauth2/authorization/google,");
        return Arrays.stream(nonAccessRoles.toString().split(",")).toArray(String[]::new);
    };

    public static String[] accessAdminRole() {

        StringBuilder adminRoles = new StringBuilder();
        adminRoles.append("/api/v1/admins/,");
        adminRoles.append("/api/v1/users/,");
        return Arrays.stream(adminRoles.toString().split(",")).toArray(String[]::new);
    }

    public static String[] accessUserRole() {

        StringBuilder adminRoles = new StringBuilder();
        adminRoles.append("/api/v1/folders/**,");
        return Arrays.stream(adminRoles.toString().split(",")).toArray(String[]::new);
    }

}
