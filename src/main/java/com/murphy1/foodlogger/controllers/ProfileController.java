package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("foodConsumedToday", userService.foodConsumedToday());

        return "profile.html";
    }

}
