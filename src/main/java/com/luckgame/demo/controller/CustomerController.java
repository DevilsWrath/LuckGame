package com.luckgame.demo.controller;

import com.luckgame.demo.user.AppUser;
import com.luckgame.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    private final UserServiceImpl customerService;

    @Autowired
    public CustomerController(UserServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<AppUser> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody AppUser customer){
        customerService.addNewCustomer(customer);
    }



}
