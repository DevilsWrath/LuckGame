package com.luckgame.demo.transactions;


import com.luckgame.demo.customer.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@Table
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long transactionId;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "customer_id")
    private Customer userID;

    private LocalDate transactionDate = LocalDate.now();

    private Float amount;

    public Transaction(Customer userID, Float amount) {
        this.userID = userID;
        this.amount = amount;
    }

    public Transaction(Float amount) {
        this.amount = amount;
    }
}
