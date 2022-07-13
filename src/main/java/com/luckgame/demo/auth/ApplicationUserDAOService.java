package com.luckgame.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.luckgame.demo.security.ApplicationUserRole.ADMIN;
import static com.luckgame.demo.security.ApplicationUserRole.CUSTOMER;

@Repository("ApplicationUserDAOService")
public class ApplicationUserDAOService  implements ApplicationUserDAO{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDAOService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(applicationUser -> applicationUser.getUsername().equals(username))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "savas",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true

                ),
                new ApplicationUser(
                        "sevim",
                        passwordEncoder.encode("password"),
                        CUSTOMER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true

                )
        );
        return applicationUsers;
    }
}
