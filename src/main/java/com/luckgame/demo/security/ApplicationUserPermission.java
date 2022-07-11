package com.luckgame.demo.security;

public enum ApplicationUserPermission {
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    MATCH_READ("match:read"),
    MATCH_WRITE("match:write");

    private final String permission;

    public String getPermission() {
        return permission;
    }

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
