package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/remove_food/{foodName}")
    public String removeFood(@PathVariable String foodName){
        userService.removeFood(foodName);

        return "redirect:/profile";
    }

}
