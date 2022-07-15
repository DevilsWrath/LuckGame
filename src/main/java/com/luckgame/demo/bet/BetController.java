package com.luckgame.demo.bet;


import com.luckgame.demo.service.BetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bet")
public class BetController {
    private final BetServiceImpl betService;

    @Autowired
    public BetController(BetServiceImpl betService) {
        this.betService = betService;
    }

   @GetMapping
    public List<Bet> getBets(){
         return betService.getBets();
    }

    @PostMapping
    public void registerNewBet(@RequestBody Bet bet){
        betService.saveBet(bet);
    }

}
