package com.luckgame.demo.customer;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/customers")
public class CustomerManagementController {

    private static final List<Customer> CUSTOMERS = Arrays.asList(
      new Customer(1L,"savas","password" ,"savas@gmail.com"),
      new Customer(2L,"sevim","password" ,"sevim@gmail.com"),
      new Customer(3L,"ali","password" ,"ali@gmail.com")

    );
    @GetMapping
    public List<Customer> getCustomers() {
        return CUSTOMERS;
    }
    @PostMapping
    public void registerCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
    }
    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id) {
        System.out.println(id);;
    }
    @PutMapping("{id}")
    public void updateCustomer(@PathVariable Long id, Customer customer) {
        System.out.println(String.format("%s %s", id, customer));
    }
}
