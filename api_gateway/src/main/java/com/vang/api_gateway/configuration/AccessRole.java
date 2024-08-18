package com.vang.api_gateway.configuration;

import java.util.Arrays;

public class AccessRole {

    public static String[] nonAccessRoles() {

        StringBuilder nonAccessRoles = new StringBuilder();
        nonAccessRoles.append("/api/v1/public-store/,");
        return Arrays.stream(nonAccessRoles.toString().split(",")).toArray(String[]::new);
    };
}
