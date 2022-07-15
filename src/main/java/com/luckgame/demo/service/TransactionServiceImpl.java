package com.luckgame.demo.service;

import com.luckgame.demo.repo.TransactionRepo;
import com.luckgame.demo.transactions.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;

    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepo.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepo.findAll();
    }

    @Override
    public Transaction getTransaction(Long transactionId) {
        return transactionRepo.findByTransactionId(transactionId);
    }

    @Override
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepo.findByUserID(userId);
    }
}

