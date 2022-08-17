package com.luckgame.demo.service;

import com.luckgame.demo.Role.Role;
import com.luckgame.demo.repo.RoleRepo;
import com.luckgame.demo.user.AppUser;
//import com.luckgame.demo.domain.Role;
import com.luckgame.demo.repo.UserRepo;
//import com.luckgame.demo.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final RoleServiceImpl roleService;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, RoleServiceImpl roleService) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.roleService = roleService;
    }

    public void addNewCustomer(AppUser user){
      Optional<AppUser> customerOptional = userRepo.findByEmail(user.getEmail());
        if(customerOptional.isPresent()){
            throw new IllegalStateException("Customer with email " + user.getEmail() + " already exists");
        }
        user.setActive(true);
        roleService.addRoleToUser(user);

         userRepo.save(user);
    }
}
