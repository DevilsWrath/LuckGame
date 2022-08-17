package com.luckgame.demo.user;


import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/customers")
public class UserManagementController {

    private static final List<AppUser> APP_USERS = Arrays.asList(
      new AppUser(1L,"savas","password" ,"savas@gmail.com"),
      new AppUser(2L,"sevim","password" ,"sevim@gmail.com")
    );
    @GetMapping
    public List<AppUser> getUsers() {
        return APP_USERS;
    }
    @PostMapping
    public void registerUser(@RequestBody AppUser customer) {
        System.out.println(customer);
    }
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println(id);
    }
    @PutMapping("{id}")
    public void updateUser(@PathVariable Long id, AppUser appUser) {
        System.out.println(String.format("%s %s", id, appUser));
    }
}
