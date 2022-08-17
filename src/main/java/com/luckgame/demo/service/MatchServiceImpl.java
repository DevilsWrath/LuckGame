package com.luckgame.demo.service;

import com.luckgame.demo.bet.Bet;
import com.luckgame.demo.matches.Match;
import com.luckgame.demo.repo.MatchRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MatchServiceImpl implements MatchService {

    private final MatchRepo matchRepo;

    private final BetServiceImpl betService;

    @Override
    public Match saveMatch(Match match) {
        return matchRepo.save(match);
    }

    @Override
    public Match getMatch(Long matchId) {
        return matchRepo.findByMatchId(matchId);
    }

    @Override
    public List<Match> getMatches() throws ParseException {
        List<Match> matches = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = sdf.parse(sdf.format(new Date()));
        for (Match match:matchRepo.findAll()) {

            Date date = sdf.parse(match.getMatchDate());
            if (date.after(currentDate) && !match.isResulted()) {
                matches.add(match);
            }

        }
        return matches;
    }
}

