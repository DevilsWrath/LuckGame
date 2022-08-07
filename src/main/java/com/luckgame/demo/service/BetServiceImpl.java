package com.luckgame.demo.service;

import com.luckgame.demo.bet.Bet;
import com.luckgame.demo.matches.Match;
import com.luckgame.demo.repo.BetRepo;
import com.luckgame.demo.repo.MatchRepo;
import com.luckgame.demo.repo.UserRepo;
import com.luckgame.demo.transactions.Transaction;
import com.luckgame.demo.user.AppUser;
import freemarker.template.utility.NullArgumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
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
        if (bet.getAmount() < 0 && user.getBalance() < bet.getAmount()) {
            throw new IllegalArgumentException("Bet cannot be registered");
        }else {
            Bet newBet = new Bet(user, match ,bet.getAmount(), bet.getBetType(), bet.getBetId());
            betRepo.save(newBet);
        }
    }

    @Override
    public List<Bet> getBets() {
        return betRepo.findAll();
    }

    @Override
    public void setBetResult(Bet bet) {
        for (Bet b : getBets()) {
             if (b.getMatchID() == bet.getMatchID()) {
                b.setBetResult(bet.getBetResult());
            }
        }
        for (Bet b : getBets()) {
            if (bet.getBetResult() == b.getBetType()) {
                bet.getUserID().setBalance(bet.getUserID().getBalance() + bet.getAmount());
            }
        }

    }
}
