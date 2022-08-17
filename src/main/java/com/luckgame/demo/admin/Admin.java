package com.luckgame.demo.admin;

import com.luckgame.demo.bet.Bet;
import com.luckgame.demo.bet.Results;
import com.luckgame.demo.repo.BetRepo;
import com.luckgame.demo.repo.TransactionRepo;
import com.luckgame.demo.repo.UserRepo;
import com.luckgame.demo.user.AppUser;
import com.luckgame.demo.matches.Match;
import com.luckgame.demo.transactions.Transaction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class Admin {
    private final UserRepo userRepo;

    private final TransactionRepo transactionRepo;

    private final BetRepo betRepo;

    public Admin(UserRepo userRepo , TransactionRepo transactionRepo, BetRepo betRepo) {
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
        this.betRepo = betRepo;
    }
    public Float totalBalance() {

        Float systemGain = 0f;

        List<Transaction> transactions = transactionRepo.findAll();
        List<Bet> bets =  betRepo.findAll();
        for (Transaction transaction : transactions) {
              systemGain = systemGain + transaction.getAmount();

        }
        for (Bet bet : bets) {
             if (bet.getResults() == Results.WIN) {
                 systemGain = systemGain - bet.getAmount();
             } else {
                 systemGain = systemGain + bet.getAmount();
             }

        }
        return systemGain;
    }

}
