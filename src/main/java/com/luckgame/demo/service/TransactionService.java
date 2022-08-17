package com.luckgame.demo.service;

import com.luckgame.demo.transactions.Transaction;
import com.luckgame.demo.user.AppUser;

import java.util.List;

public interface TransactionService {
    void addTransaction(Transaction transaction);
    List<Transaction> getTransactions();
    Transaction getTransaction(Long transactionId);
    List<Transaction>getTransactionsByUserId(Long userId);
}

