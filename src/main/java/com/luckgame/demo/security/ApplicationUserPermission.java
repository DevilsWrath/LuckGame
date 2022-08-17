package com.luckgame.demo.security;

public enum ApplicationUserPermission {
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    ADMIN_READ("match:read"),
    ADMIN_WRITE("match:write");

    private final String permission;

    public String getPermission() {
        return permission;
    }

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
