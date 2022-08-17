package com.luckgame.demo.controller;

import com.luckgame.demo.service.TransactionServiceImpl;
import com.luckgame.demo.transactions.Transaction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping("/api/v1/transactions")
    public List<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }

    @RequestMapping("/transactions")
    public String addNewTransaction(@PathVariable Long id, Model model){
        model.addAttribute("transaction", transactionService.getTransactionsByUserId(id));
        return "transactions";

    }

}
