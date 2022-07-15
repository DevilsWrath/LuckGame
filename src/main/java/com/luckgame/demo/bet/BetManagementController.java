package com.luckgame.demo.bet;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/bets")
public class BetManagementController {

    private static final List<Bet> BETS = Arrays.asList(
      new Bet(500f,(byte)1)
    );

    @GetMapping
    public List<Bet> getBets() {
        return BETS;
    }
    @PostMapping
    public void registerNewBet(@RequestBody Bet bet) {
        System.out.println(bet);
    }

    @DeleteMapping("{id}")
    public void deleteBet(@PathVariable Long id) {
        System.out.println(id);
    }
}
