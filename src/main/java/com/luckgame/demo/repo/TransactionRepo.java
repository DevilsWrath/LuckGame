package com.luckgame.demo.repo;

import com.luckgame.demo.customer.Customer;
import com.luckgame.demo.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    Transaction findByTransactionId(Long transactionId);
    List<Transaction> findByUserID(Long userID);

}

