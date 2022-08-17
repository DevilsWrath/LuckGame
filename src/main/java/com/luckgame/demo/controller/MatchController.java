package com.luckgame.demo.controller;

import com.luckgame.demo.matches.Match;
import com.luckgame.demo.repo.UserRepo;
import com.luckgame.demo.service.MatchServiceImpl;
import com.luckgame.demo.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@Controller
public class MatchController {

    private final MatchServiceImpl matchService;

    private final UserRepo userRepo;

    @Autowired
    public MatchController(MatchServiceImpl matchService, UserRepo userRepo) {
        this.matchService = matchService;
        this.userRepo = userRepo;
    }

    @RequestMapping("/matches")
    public String getMatches(Model model) throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        AppUser user = userRepo.findUserByUsername(currentUserName);
        Float userBalance = user.getBalance();
        int matchCount = matchService.getMatches().size();
        model.addAttribute("count", matchCount);
        model.addAttribute("userBalance", "Welcome back " + user.getUsername() + "! Your balance: " + userBalance);
        model.addAttribute("user", user);
        List<Match> list = matchService.getMatches();

        model.addAttribute("matches", list);
        return "matches";
    }
}
