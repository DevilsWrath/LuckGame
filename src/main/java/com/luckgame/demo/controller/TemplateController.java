package com.luckgame.demo.controller;

import com.luckgame.demo.matches.Match;
import com.luckgame.demo.repo.UserRepo;
import com.luckgame.demo.service.MatchServiceImpl;
import com.luckgame.demo.service.MyUserDetailsService;
import com.luckgame.demo.service.UserServiceImpl;
import com.luckgame.demo.user.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/")
public class TemplateController {

    private final MatchServiceImpl matchService;

    private final UserRepo userRepo;

    public TemplateController(MatchServiceImpl matchService, UserRepo userRepo) {
        this.matchService = matchService;
        this.userRepo = userRepo;
    }

    @RequestMapping("/")
    public String index(Model model) throws ParseException {
        List<Match> matchList = matchService.getMatches();
        int matchCount = matchList.size();

        model.addAttribute("matches", matchList);
        model.addAttribute("count", matchCount);


        return "index";
    }

    @GetMapping("/login")
    public String getLoginView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        AppUser user = userRepo.findUserByUsername(currentUserName);

        if (isAuthenticated()) {
            if ((user.hasRole("ADMIN"))) {
                return "redirect:/admin";
            } else {
                return "redirect:/matches";
            }
        }

        model.addAttribute("loginUser", new AppUser());
        return "login";
    }
    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
    }





