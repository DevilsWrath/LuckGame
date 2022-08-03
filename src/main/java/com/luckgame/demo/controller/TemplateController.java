package com.luckgame.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class TemplateController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginView(){
        return "login";
    }

    @GetMapping("/matches")
    public String getMatches(){
        return "matches";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "admin";
    }


}

