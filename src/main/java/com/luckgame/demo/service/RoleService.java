package com.luckgame.demo.service;

import com.luckgame.demo.Role.Role;
import com.luckgame.demo.repo.RoleRepo;
import com.luckgame.demo.repo.UserRepo;
import com.luckgame.demo.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserRepo userRepo;

    public List<Role> findAll(){
        return roleRepo.findAll();
    }
    public Optional<Role> findById(Long id){
        return roleRepo.findById(id);
    }
    public Role save(Role role){
        return roleRepo.save(role);
    }
    public void assignRoleToUser(Long roleId, Long userId){
        AppUser user = userRepo.findById(userId).get();
        Role role = roleRepo.findById(roleId).get();
        Set<Role> userRole = user.getRoles();
        userRole.add(role);
        user.setRoles(userRole);
        userRepo.save(user);

    }
}
