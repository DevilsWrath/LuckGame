package com.luckgame.demo.service;

import com.luckgame.demo.bet.Bet;

import java.util.List;

public interface BetService {
    Bet saveBet(Bet bet);
    List<Bet> getBets();
   // Bet findBetById(Long id);
}
