package com.luckgame.demo.service;

import com.luckgame.demo.matches.Match;

import java.util.List;

public interface MatchService {
    Match saveMatch(Match match);
    Match getMatch(String matchId);
    List<Match> getMatches();
}
