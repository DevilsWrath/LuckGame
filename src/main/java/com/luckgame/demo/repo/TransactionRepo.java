package com.luckgame.demo.repo;

import com.luckgame.demo.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    Transaction findByTransactionId(Long transactionId);
    List<Transaction> findByUserID(Long userID);

}

