package com.luckgame.demo.bet;

import com.luckgame.demo.repo.BetRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BetConfig {

    @Bean
    CommandLineRunner commandLineRunnerBet(BetRepo betRepo) {
        return args -> {
            betRepo.save(new Bet(500f, (byte)1));
            betRepo.save(new Bet(100f, (byte)0));
        };
    }
}
