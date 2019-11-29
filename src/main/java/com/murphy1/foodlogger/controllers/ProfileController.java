package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.model.Goals;
import com.murphy1.foodlogger.model.User;
import com.murphy1.foodlogger.repositories.UserRepository;
import com.murphy1.foodlogger.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ProfileController {

    private UserService userService;
    private UserRepository userRepository;

    public ProfileController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("foodConsumedToday", userService.foodConsumedToday());
        model.addAttribute("goalDetails", userService.dailyProgressToGoal());

        // set default goal values if user has not set them
        if (userService.getCurrentUser().getGoals() == null){
            model.addAttribute("goals", userService.customizedDailyGoals());
        }else{
            model.addAttribute("goals", userService.getCurrentUser().getGoals());
        }

        return "profile.html";
    }

    @GetMapping("/remove_food/{foodName}")
    public String removeFood(@PathVariable String foodName, Model model){
        userService.removeFood(foodName);

        return "redirect:/profile";
    }

    @GetMapping("/update/goals/daily")
    public String updateDailyGoals(Model model){
        model.addAttribute("customizedDailyGoals", userService.customizedDailyGoals());
        model.addAttribute("goals", new Goals());

        return "goals/dailygoals.html";
    }

    @PostMapping("/update/goals/daily/save")
    public String saveDailyGoals(Model model, @ModelAttribute("goals") Goals goals){
        User user = userService.getCurrentUser();

        Map<String, String> newGoals = new HashMap<>();
        newGoals.put("Calories", goals.getCalories()+"kcal");
        newGoals.put("Sugars", goals.getSugars()+"g");
        newGoals.put("Fat", goals.getFat()+"g");
        newGoals.put("Sodium", goals.getSodium()+"mg");
        newGoals.put("Fiber", goals.getFiber()+"g");
        newGoals.put("Protein", goals.getProtein()+"g");
        newGoals.put("Potassium", goals.getPotassium()+"mg");
        newGoals.put("Carbs", goals.getCarbs()+"g");

        user.setGoals(newGoals);
        userRepository.save(user).block();

        return "redirect:/profile";
    }
}
