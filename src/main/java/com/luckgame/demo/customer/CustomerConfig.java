package com.luckgame.demo.customer;

import com.luckgame.demo.repo.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepo customerRepo) {
        return args -> {
            customerRepo.save(new Customer("Savas", "password" , "savas@gmail.com"));
            customerRepo.save(new Customer("Sevim", "password","sevim@gmail.com"));
        };
    }
}
