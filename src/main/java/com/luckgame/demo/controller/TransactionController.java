package com.luckgame.demo.controller;

import com.luckgame.demo.service.MyUserDetails;
import com.luckgame.demo.service.TransactionServiceImpl;
import com.luckgame.demo.transactions.Transaction;
import com.luckgame.demo.user.AppUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransactionController {

    @GetMapping("/transactions")
    public String getTransaction(@AuthenticationPrincipal MyUserDetails user, Model model) {
       model.addAttribute("Transaction", new Transaction());

        return "transactions";
    }



    private float amount;
    private boolean isDeposit;



    private TransactionServiceImpl transactionService;
    private MyUserDetails myUserDetails;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
        this.myUserDetails = new MyUserDetails();
    }

    @PostMapping(value = "/registerTransaction")
    public String addTransaction(@ModelAttribute Transaction transaction) {


        transactionService.addTransaction(transaction);
        return "matches";
    }
}
