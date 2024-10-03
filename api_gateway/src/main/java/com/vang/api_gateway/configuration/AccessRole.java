package com.vang.api_gateway.configuration;

import java.util.Arrays;

public class AccessRole {

    public static String[] nonAccessRoles() {

        StringBuilder nonAccessRoles = new StringBuilder();
        nonAccessRoles.append("/api/v1/public-store/,");
        nonAccessRoles.append("/api/v1/auth/admin/,");
        nonAccessRoles.append("/api/v1/auth/user/,");
        nonAccessRoles.append("/api/v1/forgot-password/**,");
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

        StringBuilder userRoles = new StringBuilder();
        userRoles.append("/api/v1/folders/**,");
        userRoles.append("/api/v1/files/**,");
        userRoles.append("/api/v1/trash/**,");
        return Arrays.stream(userRoles.toString().split(",")).toArray(String[]::new);
    }

    public static String[] accessUserRoleWithPostMethod() {

        StringBuilder userRoles = new StringBuilder();
        userRoles.append("/api/v1/users/");
        return Arrays.stream(userRoles.toString().split(",")).toArray(String[]::new);
    }

}
