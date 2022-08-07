package com.luckgame.demo.repo;

import com.luckgame.demo.bet.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepo extends JpaRepository<Bet, Long> {

    Bet findBetByBetType(Byte betType);
    @Query("select b from Bet b where b.betId = ?1")
    Bet findByBetId(Long betId);
}
