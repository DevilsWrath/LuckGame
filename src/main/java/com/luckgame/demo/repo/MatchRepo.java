package com.luckgame.demo.repo;

import com.luckgame.demo.matches.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepo extends JpaRepository<Match, Long> {
    Optional<Match> findMatchByMatchId(Long matchId);
    Match findByMatchId(Long matchId);
}

