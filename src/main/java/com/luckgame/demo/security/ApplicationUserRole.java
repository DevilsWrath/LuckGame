package com.luckgame.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    CUSTOMER("customer", Set.of(ApplicationUserPermission.CUSTOMER_READ, ApplicationUserPermission.CUSTOMER_WRITE)),
    ADMIN("admin", Set.of(ApplicationUserPermission.CUSTOMER_READ, ApplicationUserPermission.CUSTOMER_WRITE, ApplicationUserPermission.ADMIN_READ, ApplicationUserPermission.ADMIN_WRITE));

    private final Set<ApplicationUserPermission> permission;

    ApplicationUserRole(String role, Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }

    public Set<ApplicationUserPermission> getPermission() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}


