package com.luckgame.demo.service;

import com.luckgame.demo.matches.Match;
import com.luckgame.demo.repo.MatchRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MatchServiceImpl implements MatchService {

    private final MatchRepo matchRepo;

    @Override
    public Match saveMatch(Match match) {
        return matchRepo.save(match);
    }

    @Override
    public Match getMatch(Long matchId) {
        return matchRepo.findByMatchId(matchId);
    }

    @Override
    public List<Match> getMatches() {
        List<Match> matches = new ArrayList<>();
        matchRepo.findAll().forEach(matches::add);

        return matches;
    }

    public void addNewMatch(Match match){
        Optional<Match> matchOptional = matchRepo.findMatchByMatchId(match.getMatchId());
        if(matchOptional.isPresent()){
            throw new IllegalStateException("Match with matchId " + match.getMatchId() + " already exists");
        }else {
            matchRepo.save(match);
        }
    }
}

