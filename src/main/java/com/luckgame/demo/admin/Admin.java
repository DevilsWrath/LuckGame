package com.luckgame.demo.admin;

import com.luckgame.demo.user.AppUser;
import com.luckgame.demo.matches.Match;
import com.luckgame.demo.transactions.Transaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class Admin {


    public Float totalBalance(AppUser customer, Transaction transaction) {
        float customerBalance = customer.getBalance();
        float transactionAmount = transaction.getAmount();
        boolean isDeposit = transaction.getIsDeposit();
        if (!isDeposit){
            float amount = -transactionAmount;
        }

        if (customerBalance < transactionAmount) {
            float amount = transactionAmount - customerBalance;
            return amount;
        } else {
           float amount = customerBalance - transactionAmount;
           return -amount;
        }
    }

    @PostMapping("/matches")
    public void setNewMatch(Match match) {

    }
}
