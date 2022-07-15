package com.luckgame.demo.transactions;

import com.luckgame.demo.customer.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionManagementController {

    private static final List<Transaction> TRANSACTIONS = Arrays.asList(
      new Transaction(2000f)
    );

    @GetMapping
    public List<Transaction> getTransactions() {
        return TRANSACTIONS;
    }

    @PostMapping
    public void addTransaction(@RequestBody Transaction transaction) {
        System.out.println(transaction);
    }
}
