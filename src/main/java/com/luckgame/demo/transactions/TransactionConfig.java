package com.luckgame.demo.transactions;

import com.luckgame.demo.customer.Customer;
import com.luckgame.demo.repo.CustomerRepo;
import com.luckgame.demo.repo.TransactionRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {

    @Bean
    CommandLineRunner commandLineRunnerTransaction(TransactionRepo transactionRepo) {
        return args -> {
            transactionRepo.save(new Transaction(2000f));

        };
    }
}
