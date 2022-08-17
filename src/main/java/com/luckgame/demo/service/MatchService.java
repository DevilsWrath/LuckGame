package com.luckgame.demo.service;

import com.luckgame.demo.matches.Match;

import java.text.ParseException;
import java.util.List;

public interface MatchService {
    Match saveMatch(Match match);
    Match getMatch(Long matchId);
    List<Match> getMatches() throws ParseException;
}
