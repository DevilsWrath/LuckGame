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
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepo customerRepo;

    @Autowired
    private final RoleRepo roleRepo;

    public void saveUserWithDefaultRole(AppUser user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setActive(true);

        Role userRole = roleRepo.findByName("ROLE_USER");
        user.addRole(userRole);
        customerRepo.save(user);
    }


    public List<AppUser> getCustomers(){
        return customerRepo.findAll();
    }
    public void addNewCustomer(AppUser customer){
      Optional<AppUser> customerOptional = customerRepo.findByEmail(customer.getEmail());
        if(customerOptional.isPresent()){
            throw new IllegalStateException("Customer with email " + customer.getEmail() + " already exists");
        }
         customerRepo.save(customer);
    }

    @Override
    public AppUser saveCustomer(AppUser customer) {
        return customerRepo.save(customer);
    }

   // @Override
   // public Role saveRole(Role role) {
     //   return roleRepo.save(role);
   // }

    /*@Override
    public void addRoleToUser(String username, String roleName){
        AppUser customer = customerRepo.findUserByUsername(username);
        Role role = roleRepo.findByName(roleName);
        customer.getRoles();
    }*/

    @Override
    public AppUser getCustomer(String username) {
        return customerRepo.findUserByUsername(username);
    }

    @Override
    public void setCustomerBalance(AppUser customer) {
        customerRepo.findByID(customer.getID()).setBalance(customer.getBalance());
    }
}
