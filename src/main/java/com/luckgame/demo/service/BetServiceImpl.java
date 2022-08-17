package com.luckgame.demo.service;

import com.luckgame.demo.bet.Bet;
import com.luckgame.demo.repo.BetRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BetServiceImpl implements BetService {

    private final BetRepo betRepo;

    @Override
    public Bet saveBet(Bet bet) {
        return betRepo.save(bet);
    }

    @Override
    public List<Bet> getBets() {
        return betRepo.findAll();
    }

  //  @Override
   // public Bet findBetById(Long id) {
  //      return betRepo.findById(id).orElseThrow(() -> new IllegalStateException("Bet with id " + id + " does not exist"));
  //  }
}
