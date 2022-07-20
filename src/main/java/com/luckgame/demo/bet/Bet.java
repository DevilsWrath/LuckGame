package com.luckgame.demo.bet;

import com.luckgame.demo.user.AppUser;
import com.luckgame.demo.matches.Match;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Bet {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long betId;

   // @ManyToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name = "user_id")
    //private AppUser userID;

    //@ManyToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name = "match_id")
    //private Match matchID;

    private Float amount;
    private Byte betType;
    private Byte betResult;

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

   // public AppUser getUserID() {
  //      return UserID;
   // }

   // public Match getMatchID() {
   //     return matchID;
   // }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Byte getBetType() {
        return betType;
    }

    public void setBetType(Byte betType) {
        this.betType = betType;
    }

    public Byte getBetResult() {
        return betResult;
    }

    public void setBetResult(Byte betResult) {
        this.betResult = betResult;
    }

    public Bet(Float amount, Byte betType) {
        this.amount = amount;
        this.betType = betType;
    }
}
