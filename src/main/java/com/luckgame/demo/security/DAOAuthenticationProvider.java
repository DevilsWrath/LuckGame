package com.luckgame.demo.security;

import com.luckgame.demo.user.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class DAOAuthenticationProvider {

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            AppUser user = new AppUser();
            if (user.getUsername().equals(authentication.getName())) {
                return authentication;
            } else {
                throw new AuthenticationException("Username not found") {
                };
            }
        }  catch (Exception e) {
            throw new RuntimeException("User not found");
    }
    }
}

