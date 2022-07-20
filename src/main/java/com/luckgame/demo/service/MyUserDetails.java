package com.luckgame.demo.service;

import com.luckgame.demo.Role.Role;
import com.luckgame.demo.security.PasswordConfig;
import com.luckgame.demo.user.AppUser;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    PasswordConfig passwordConfig = new PasswordConfig();


    public MyUserDetails(AppUser appUser) {

        this.user = appUser;
        String encodedPassword = passwordConfig.passwordEncoder().encode(appUser.getPassword());

        this.username = appUser.getUsername();
        this.password = encodedPassword;
        this.active = appUser.isActive();
            this.authorities = appUser.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" +role.getName()))
                    .collect(Collectors.toList());
    }

    public boolean hasRole(String roleName) {
        return this.user.hasRole(roleName);
    }

    @Autowired
    private AppUser user;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}



