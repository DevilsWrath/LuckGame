package com.luckgame.demo.service;

import com.luckgame.demo.customer.Customer;
import com.luckgame.demo.domain.Role;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    Customer getCustomer(String username);
    List<Customer> getCustomers();

}
