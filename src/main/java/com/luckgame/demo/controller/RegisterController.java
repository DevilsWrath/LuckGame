package com.luckgame.demo.controller;

import com.luckgame.demo.service.UserServiceImpl;
import com.luckgame.demo.user.AppUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    private final UserServiceImpl userService;

    public RegisterController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterView(Model model) {
        model.addAttribute("registerUser", new AppUser());
        return "register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegisterView(@ModelAttribute AppUser user) {
       if (isAuthenticated()) {
           return "redirect:/matches";
       }
        if (user.getEmail() != null ||
                user.getPassword() != null ||
                user.getUsername() != null) {
            return "redirect:/register";
        } else {
            String userEmail = user.getEmail();
            if (!userEmail.contains("@") || !userEmail.contains(".")) {
                return "redirect:/register";
            } else {
                userService.addNewCustomer(user);
                return "/login";
            }
        }
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
