package com.luckgame.demo.service;

import com.luckgame.demo.customer.Customer;
import com.luckgame.demo.domain.Role;
import com.luckgame.demo.repo.CustomerRepo;
import com.luckgame.demo.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final RoleRepo roleRepo;

    public List<Customer> getCustomers(){
        return customerRepo.findAll();
    }
    public void addNewCustomer(Customer customer){
      Optional<Customer> customerOptional = customerRepo.findCustomerByEmail(customer.getEmail());
        if(customerOptional.isPresent()){
            throw new IllegalStateException("Customer with email " + customer.getEmail() + " already exists");
        }
         customerRepo.save(customer);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName){
        Customer customer = customerRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        customer.getRoles().add(role);
    }

    @Override
    public Customer getCustomer(String username) {
        return null;
    }

}
