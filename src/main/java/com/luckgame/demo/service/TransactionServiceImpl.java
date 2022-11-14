package com.luckgame.demo.service;

import com.luckgame.demo.repo.TransactionRepo;
import com.luckgame.demo.repo.UserRepo;
import com.luckgame.demo.transactions.Transaction;
import com.luckgame.demo.user.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;
    private final UserRepo userRepo;

    @Override
    @Transactional(dontRollbackOn = IllegalArgumentException.class)
    public void addTransaction(Transaction transaction) throws IllegalArgumentException{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = authentication.getName();
           AppUser user = userRepo.findUserByUsername(currentUserName);
        if (transaction.getAmount() < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        Transaction newTransaction = new Transaction(user, transaction.getAmount(), transaction.getIsDeposit());
        transactionRepo.save(newTransaction);
        if (transaction.getIsDeposit()) {
            user.setBalance(user.getBalance() + transaction.getAmount());
        } else {
            if (user.getBalance() < transaction.getAmount()) {
                throw new IllegalArgumentException("Not enough money");
            }
            user.setBalance(user.getBalance() - transaction.getAmount());
        }


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

