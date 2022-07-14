package com.luckgame.demo.matches;

import com.luckgame.demo.service.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/matches")
public class MatchController {

    private final MatchServiceImpl matchService;

    @Autowired
    public MatchController(MatchServiceImpl matchService) {
        this.matchService = matchService;
    }

    @RequestMapping("/api/v1/matches")
    public String getMatches() {
        return "matches";
    }
    @PostMapping("/api/v1/matches/admin")
    public void addMatch(@RequestBody Match match) {
        matchService.addNewMatch(match);

    }
}
