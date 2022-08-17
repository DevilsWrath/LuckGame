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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private AppUser userID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "match_id")
    private Match matchID;

    private Float amount;

    private BetTypes betType;
    private BetTypes betResult;

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    public AppUser getUserID() {
        return userID;
    }

    public Match getMatchID() {
        return matchID;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public BetTypes getBetType() {
        return betType;
    }

    public void setBetType(BetTypes betType) {
        this.betType = betType;
    }

    public BetTypes getBetResult() {
        return betResult;
    }

    public void setBetResult(BetTypes betResult) {
        this.betResult = betResult;
    }

    public void setUserID(AppUser userID) {
        this.userID = userID;
    }

    public void setMatchID(Match matchID) {
        this.matchID = matchID;
    }

    public Bet(AppUser appUser, Match matchID, Float amount, BetTypes betType, Long betId) {
        this.userID = appUser;
        this.amount = amount;
        this.betType = betType;
        this.betId = betId;
        this.matchID = matchID;

    }
}
