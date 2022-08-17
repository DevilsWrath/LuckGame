package com.luckgame.demo.service;

import com.luckgame.demo.bet.Bet;
import com.luckgame.demo.matches.Match;
import freemarker.template.utility.NullArgumentException;

import java.util.List;

public interface BetService {
    void saveBet(Bet bet) throws IllegalArgumentException;
    List<Bet> getBets();
    void setBetResult(Bet bet);
    List<Bet> getBetsByUser(String username);
}
