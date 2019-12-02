package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.model.Goals;
import com.murphy1.foodlogger.model.User;
import com.murphy1.foodlogger.repositories.UserRepository;
import com.murphy1.foodlogger.services.AnalyticsService;
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
    private AnalyticsService analyticsService;
    private UserRepository userRepository;

    public ProfileController(UserService userService, AnalyticsService analyticsService, UserRepository userRepository) {
        this.userService = userService;
        this.analyticsService = analyticsService;
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String profile(Model model){
        Map<String, Integer> dailyProgressToGoal = userService.dailyProgressToGoal();
        Map<String, Integer> goal = new HashMap<>();

        // set default goal values if user has not set them
        if (userService.getCurrentUser().getGoals() == null){
            goal = userService.customizedDailyGoals();
        }else{
            goal = userService.getCurrentUser().getGoals();
        }

        model.addAttribute("foodConsumedToday", userService.foodConsumedToday());
        model.addAttribute("goalDetails", dailyProgressToGoal);
        model.addAttribute("goals", goal);

        model.addAttribute("currentDailyProgressPercentage", analyticsService.percentageToGoal(dailyProgressToGoal, goal));

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

        Map<String, Integer> newGoals = new HashMap<>();
        newGoals.put("Calories", goals.getCalories());
        newGoals.put("Sugars", goals.getSugars());
        newGoals.put("Fat", goals.getFat());
        newGoals.put("Sodium", goals.getSodium());
        newGoals.put("Fiber", goals.getFiber());
        newGoals.put("Protein", goals.getProtein());
        newGoals.put("Potassium", goals.getPotassium());
        newGoals.put("Carbs", goals.getCarbs());

        user.setGoals(newGoals);
        userRepository.save(user).block();

        return "redirect:/profile";
    }
}
