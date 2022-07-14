package com.luckgame.demo.matches;


import com.luckgame.demo.repo.MatchRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MatchConfig {

    @Bean
    CommandLineRunner commandLineRunnerMatch(MatchRepo matchRepo) {
        return args -> {
            matchRepo.save(new Match("Fenerbahçe vs. Beşiktaş", "23/09/2023", 1.10f, 1.20f, 1.05f));
            matchRepo.save(new Match("Ümraniyespor vs. Galatasaray","29/09/2023" , 1.20f,1.30f, 1.15f));
        };
    }
}
