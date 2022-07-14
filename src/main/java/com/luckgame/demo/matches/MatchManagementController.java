package com.luckgame.demo.matches;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/matches")
public class MatchManagementController {

    private static final List<Match> MATCHES = Arrays.asList(
      new Match(1L,"Fenerbahçe vs. Beşiktaş","23/09/2023" , 1.10f,1.20f, 1.05f),
        new Match(2L,"Ümraniyespor vs. Galatasaray","29/09/2023" , 1.20f,1.30f, 1.15f)
    );

    @GetMapping
    public List<Match> getMatches() {
        return MATCHES;
    }

    @PostMapping
    public void registerMatch(@RequestBody Match match) {
        System.out.println(match);
    }

    @DeleteMapping("{id}")
    public void deleteMatch(@PathVariable Long id) {
        System.out.println(id);;
    }

    @PutMapping("{id}")
    public void updateMatch(@PathVariable Long id, Match match) {
        System.out.println(String.format("%s %s", id, match));
    }
}
