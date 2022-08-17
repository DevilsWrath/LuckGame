package com.luckgame.demo.service;

import com.luckgame.demo.Role.Role;
import com.luckgame.demo.repo.RoleRepo;
import com.luckgame.demo.repo.UserRepo;
import com.luckgame.demo.user.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public RoleServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public void addRoleToUser(AppUser user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.findByName("CUSTOMER"));
        user.setRoles(roles);

    }
}

