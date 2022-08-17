package com.luckgame.demo.repo;

import com.luckgame.demo.bet.Bet;
import com.luckgame.demo.matches.Match;
import com.luckgame.demo.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepo extends JpaRepository<Bet, Long> {

    @Query("select b from Bet b where b.betId = ?1")
    Bet findByBetId(Long betId);
    @Query("select b from Bet b where b.user = ?1")
    List<Bet> findByUser(AppUser user);
    @Query("select b from Bet b where b.matchID = ?1")
    List<Bet> findByMatchID(Match matchID);
}
