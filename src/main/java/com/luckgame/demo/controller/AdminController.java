package com.luckgame.demo.controller;

import com.luckgame.demo.admin.Admin;
import com.luckgame.demo.bet.Bet;
import com.luckgame.demo.matches.Match;
import com.luckgame.demo.repo.BetRepo;
import com.luckgame.demo.repo.MatchRepo;
import com.luckgame.demo.repo.UserRepo;
import com.luckgame.demo.service.BetServiceImpl;
import com.luckgame.demo.service.MatchServiceImpl;
import com.luckgame.demo.user.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    private final UserRepo userRepo;

    private final MatchRepo matchRepo;

    private final BetRepo betRepo;

    private final Admin adminTools;

    private final MatchServiceImpl matchService;

    private final BetServiceImpl betService;

    public AdminController(UserRepo userRepo,
                           Admin adminTools,
                           MatchRepo matchRepo,
                           MatchServiceImpl matchService,
                           BetServiceImpl betService, BetRepo betRepo) {
        this.userRepo = userRepo;
        this.adminTools = adminTools;
        this.matchRepo = matchRepo;
        this.matchService = matchService;
        this.betService = betService;
        this.betRepo = betRepo;
    }

    @GetMapping("/admin")
    public String getAdminWiew(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        AppUser user = userRepo.findUserByUsername(currentUserName);

        Float systemBalance = adminTools.totalBalance();

        model.addAttribute("adminWelcome", "Welcome back " + user.getUsername() + "! You are admin!");
        model.addAttribute("systemBalance", "System balance: " + systemBalance);
        return "admin";
    }

    @GetMapping("/admin/addMatch")
    public String getAddMatchView(Model model) {
        model.addAttribute("match", new Match());
        return "add_match";
    }

    @RequestMapping( value = "/admin/addMatch" , method = RequestMethod.POST)
    public String addMatch(@ModelAttribute("match") Match match, RedirectAttributes redirectAttributes) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String now = LocalDate.now().format(formatter);
        LocalDate localDate = LocalDate.parse(now, formatter);

        LocalDate matchDateJumbled = LocalDate.parse(match.getMatchDate(), formatter2);
        String matchDateString = matchDateJumbled.format(formatter);
        LocalDate matchDate = LocalDate.parse(matchDateString, formatter);

        if (matchDate.isBefore(localDate)) {
            redirectAttributes.addFlashAttribute("message", "Match cannot be added for past dates");
            return "redirect:/admin/addMatch";
        } else {
            redirectAttributes.addFlashAttribute("message", "Match added successfully!");
            match.setMatchDate(matchDateString);
            String matchName = match.getHomeTeam() + " vs " + match.getAwayTeam();
            match.setMatchName(matchName);
            match.setResulted(false);
            matchRepo.save(match);
        }
        return "redirect:/admin";


    }

    @RequestMapping("/admin/result")
    public String AddResultView(Model model) throws ParseException {
        List<Match> matchList = matchService.getMatches();

        model.addAttribute("matches", matchList);

        return "add_result";
    }

    @GetMapping("/admin/result/{matchId}")
    public String getAddResult(@PathVariable("matchId") Long matchId, Model model) throws ParseException {

        Match match = matchRepo.findByMatchId(matchId);

        model.addAttribute("bet", new Bet(match));

        return "result_set";
    }


    @RequestMapping(value = "/admin/result/{matchId}" , method = RequestMethod.POST)
    public String addResult(@PathVariable("matchId")Long matchId,
                            @ModelAttribute("bet") Bet bet,
                            RedirectAttributes redirectAttributes,Model model) throws ParseException {

            Match match = matchRepo.findByMatchId(matchId);
            model.addAttribute("match", "Set a result for: " + match.getMatchName());
            bet.setMatchID(match);
            betService.setBetResult(bet);
            redirectAttributes.addFlashAttribute("message", "Result added successfully!");
        model.addAttribute("match", "Set a result for: " + match.getMatchName());
            return "redirect:/admin";

    }


}
