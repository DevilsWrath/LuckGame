package com.luckgame.demo.repo;

import com.luckgame.demo.bet.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepo extends JpaRepository<Bet, Long> {

    Bet findBetByBetType(Byte betType);
    Bet findBetByBetId(Long id);

}
