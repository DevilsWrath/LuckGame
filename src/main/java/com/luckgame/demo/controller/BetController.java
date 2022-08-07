package com.luckgame.demo.controller;


import com.luckgame.demo.bet.Bet;
import com.luckgame.demo.matches.Match;
import com.luckgame.demo.repo.MatchRepo;
import com.luckgame.demo.service.BetServiceImpl;
import com.luckgame.demo.service.MatchService;
import com.luckgame.demo.service.MatchServiceImpl;
import com.luckgame.demo.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BetController {
    private final BetServiceImpl betService;

    private final MatchRepo matchRepo;

    @Autowired
    public BetController(BetServiceImpl betService, MatchRepo matchRepo) {
        this.betService = betService;
        this.matchRepo = matchRepo;
    }

    @RequestMapping("/bet")
    public String listMatches(Model model) {

        List<Match> matchList = matchRepo.findAll();

        model.addAttribute("matches", matchList);

        return "bet";
    }

    @GetMapping("/bet/new/{matchId}")
    public String newBet(@PathVariable("matchId") Long matchId, Model model, Bet bet) {
        Match currentMatch = matchRepo.findByMatchId(matchId);
        bet.setMatchID(currentMatch);
        model.addAttribute("pageTitle", "New bet for " + currentMatch.getMatchName());
        model.addAttribute("match", currentMatch);
        model.addAttribute("bet", new Bet());
        return "bet_new";
    }
    @RequestMapping(value = "/bet/new/{matchId}" , method = RequestMethod.POST)
    public String saveBet(@PathVariable("matchId")Long matchId,@ModelAttribute Bet bet, RedirectAttributes redirectAttributes) {
        Match currentMatch = matchRepo.findByMatchId(matchId);
        bet.setMatchID(currentMatch);
        betService.saveBet(bet);
        redirectAttributes.addFlashAttribute("message", "Bet was successfully registered");
        return "redirect:/matches";
    }

    @RequestMapping("/admin/bets")
    public void setBetResult(@RequestBody Bet bet){
        betService.setBetResult(bet);

    }

    }

