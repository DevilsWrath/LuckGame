package com.luckgame.demo.controller;

import com.luckgame.demo.matches.Match;
import com.luckgame.demo.service.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class MatchController {

    private final MatchServiceImpl matchService;

    @Autowired
    public MatchController(MatchServiceImpl matchService) {
        this.matchService = matchService;
    }

    @RequestMapping("/matches")
    public String getMatches(Model model) {
       List<Match> list = matchService.getMatches();
        model.addAttribute("matches", list);
        return "matches";
    }

    @PostMapping("/api/v1/matches/admin")
    public void addMatch(@RequestBody Match match) {
        matchService.addNewMatch(match);

    }
}
