package com.luckgame.demo.service;

import com.luckgame.demo.bet.Bet;
import com.luckgame.demo.bet.BetTypes;
import com.luckgame.demo.bet.Results;
import com.luckgame.demo.matches.Match;
import com.luckgame.demo.repo.BetRepo;
import com.luckgame.demo.repo.MatchRepo;
import com.luckgame.demo.repo.UserRepo;
import com.luckgame.demo.user.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BetServiceImpl implements BetService {

    private final BetRepo betRepo;

    private final UserRepo userRepo;

    private final MatchRepo matchRepo;

    @Override
    public void saveBet(Bet bet) throws IllegalArgumentException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        AppUser user = userRepo.findUserByUsername(currentUserName);
        Match match = matchRepo.findByMatchId(bet.getMatchID().getMatchId());
        if (bet.getAmount() < 0) {
            throw new IllegalArgumentException("Bet cannot be registered");
        }else if (user.getBalance() < bet.getAmount()) {
            throw new IllegalArgumentException("Bet cannot be registered");
        } else {
            Bet newBet = new Bet(user, match ,bet.getAmount(), bet.getBetType(), bet.getBetId());
            betRepo.save(newBet);
            user.setBalance(user.getBalance() - bet.getAmount());
            bet.setWinAmount(0f);
        }
    }

    @Override
    public List<Bet> getBets() {
        return betRepo.findAll();
    }

    @Override
    public void setBetResult(Bet bet) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Float amount;
        Match currentMatch = matchRepo.findByMatchId(bet.getMatchID().getMatchId());
        currentMatch.setResulted(true);
        for (Bet b : getBets()) {
            if (b.getMatchID().getMatchId() == currentMatch.getMatchId()) {
                b.setBetResult(bet.getBetResult());
            }
        }
        for (Bet b : getBets()) {
            if (b.getMatchID() == bet.getMatchID()) {
            if (b.getBetResult() == b.getBetType()) {
                b.setResults(Results.WIN);
                if (b.getBetType() == BetTypes.HOME) {
                    AppUser user = b.getUser();
                    amount = currentMatch.getHomeTeamRate() * b.getAmount();
                    user.setBalance(user.getBalance() + amount);
                    b.setWinAmount(amount);
                } else if (b.getBetType() == BetTypes.DRAW) {
                    AppUser user = b.getUser();
                    amount = currentMatch.getDrawRate() * b.getAmount();
                    user.setBalance(user.getBalance() + amount);
                    b.setWinAmount(amount);
                } else if (b.getBetType() == BetTypes.AWAY) {
                    AppUser user = b.getUser();
                    amount = currentMatch.getAwayTeamRate() * b.getAmount();
                    user.setBalance(user.getBalance() + amount);
                    b.setWinAmount(amount);
                } else {
                    b.setResults(Results.LOSE);
                    currentMatch.setResulted(true);
                    b.setWinAmount(0f);
                }
            }
        }
    }
    }
    @Override
    public List<Bet> getBetsByUser(String username) {
        AppUser user = userRepo.findUserByUsername(username);
        List<Bet> bets = betRepo.findByUser(user);
        return bets;
    }
}

