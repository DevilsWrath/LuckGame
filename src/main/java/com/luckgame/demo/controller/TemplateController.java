package com.luckgame.demo.controller;

import com.luckgame.demo.matches.Match;
import com.luckgame.demo.service.MatchServiceImpl;
import com.luckgame.demo.service.MyUserDetailsService;
import com.luckgame.demo.service.UserServiceImpl;
import com.luckgame.demo.user.AppUser;
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

    public TemplateController(MatchServiceImpl matchService) {
        this.matchService = matchService;
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
        if (isAuthenticated()) {
            return "redirect:/matches";
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





