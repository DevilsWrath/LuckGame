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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo,
                           RoleServiceImpl roleService,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void addNewCustomer(AppUser user){
      Optional<AppUser> customerOptional = userRepo.findByEmail(user.getEmail());
        if(customerOptional.isPresent()){
            throw new IllegalStateException("Customer with email " + user.getEmail() + " already exists");
        }
        user.setActive(true);
        roleService.addRoleToUser(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

         userRepo.save(user);
    }
}
