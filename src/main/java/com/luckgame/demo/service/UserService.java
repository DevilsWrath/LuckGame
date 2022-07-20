package com.luckgame.demo.service;

import com.luckgame.demo.user.AppUser;
//import com.luckgame.demo.domain.Role;

import java.util.List;

public interface UserService {
    AppUser saveCustomer(AppUser customer);
   // Role saveRole(Role role);
   // void addRoleToUser(String username, String roleName);
    AppUser getCustomer(String username);
    List<AppUser> getCustomers();
    void setCustomerBalance(AppUser customer);

}
