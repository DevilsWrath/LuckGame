package com.luckgame.demo.controller;


import com.luckgame.demo.bet.Bet;
import com.luckgame.demo.matches.Match;
import com.luckgame.demo.repo.MatchRepo;
import com.luckgame.demo.repo.UserRepo;
import com.luckgame.demo.service.BetServiceImpl;
import com.luckgame.demo.service.MatchService;
import com.luckgame.demo.service.MatchServiceImpl;
import com.luckgame.demo.service.MyUserDetails;
import com.luckgame.demo.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class BetController {
    private final BetServiceImpl betService;
    private final MatchRepo matchRepo;
    private final UserRepo userRepo;

    @Autowired
    public BetController(BetServiceImpl betService, MatchRepo matchRepo, UserRepo userRepo) {
        this.betService = betService;
        this.matchRepo = matchRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/bet/new/{matchId}")
    public String newBet(@PathVariable("matchId") Long matchId, Model model, Bet bet) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        AppUser user = userRepo.findUserByUsername(currentUserName);


        Match currentMatch = matchRepo.findByMatchId(matchId);

        bet.setMatchID(currentMatch);
        model.addAttribute("pageTitle", "New bet for " + currentMatch.getMatchName());
        model.addAttribute("match", currentMatch);
        model.addAttribute("bet", new Bet());
        model.addAttribute("userBalance", "user " + user.getUsername() + " balance: " + user.getBalance());
        return "bet_new";
    }
    @RequestMapping(value = "/bet/new/{matchId}" , method = RequestMethod.POST)
    public String saveBet(@PathVariable("matchId")Long matchId,@ModelAttribute Bet bet, RedirectAttributes redirectAttributes) throws ParseException {
        Match currentMatch = matchRepo.findByMatchId(matchId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = sdf.parse(sdf.format(new Date()));
        Date matchDate = sdf.parse(currentMatch.getMatchDate());

        if (now.compareTo(matchDate) > 0) {
            redirectAttributes.addFlashAttribute("message", "Bet can't be placed for past matches");
            return "redirect:/matches";
        }

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

