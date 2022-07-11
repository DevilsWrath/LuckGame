package com.luckgame.demo.security;

import java.util.Set;

public enum ApplicationUserRole {
    CUSTOMER("customer", Set.of(ApplicationUserPermission.CUSTOMER_READ, ApplicationUserPermission.CUSTOMER_WRITE)),
    ADMIN("admin", Set.of(ApplicationUserPermission.CUSTOMER_READ, ApplicationUserPermission.CUSTOMER_WRITE, ApplicationUserPermission.MATCH_READ, ApplicationUserPermission.MATCH_WRITE));

    private final Set<ApplicationUserPermission> permission;

    ApplicationUserRole(String role, Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }


}

