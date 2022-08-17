package com.luckgame.demo.controller;

import com.luckgame.demo.service.TransactionServiceImpl;
import com.luckgame.demo.transactions.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransactionController {

    private TransactionServiceImpl transactionService;
    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/transactions")
    public String getTransaction(Model model) {
       model.addAttribute("transaction", new Transaction());

        return "transactions";
    }


    @PostMapping(value = "/transactions")
    public String addTransaction(@ModelAttribute Transaction transaction) {
        transactionService.addTransaction(transaction);
        return "redirect:/matches";
    }
}
