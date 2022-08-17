package com.luckgame.demo.transactions;


import com.luckgame.demo.user.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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
    @JoinColumn(name = "user_id")
    private AppUser userID;

    private LocalDate transactionDate = LocalDate.now();

    private Float amount;

    private Boolean isDeposit;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Boolean getDeposit() {
        return isDeposit;
    }

    public void setDeposit(Boolean deposit) {
        isDeposit = deposit;
    }

    public Transaction(AppUser appUserID, Float amount) {
        this.userID = appUserID;
        this.amount = amount;
    }

    public Transaction(Float amount) {
        this.amount = amount;
    }
}
