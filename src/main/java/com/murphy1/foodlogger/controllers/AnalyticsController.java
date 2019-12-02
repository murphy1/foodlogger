package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.services.AnalyticsService;
import com.murphy1.foodlogger.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class AnalyticsController {

    private UserService userService;
    private AnalyticsService analyticsService;

    public AnalyticsController(UserService userService, AnalyticsService analyticsService) {
        this.userService = userService;
        this.analyticsService = analyticsService;
    }

    @GetMapping("/view/analytics")
    public String viewAnalytics(Model model){

        Map<String, Integer> nutrientsConsumedThisWeek = analyticsService.nutrientsConsumedCurrentWeek();
        Map<String, Integer> weeklyGoal = analyticsService.getWeeklyCustomizedGoalStats();
        Map<String, Integer> nutrientsConsumedThisMonth = analyticsService.nutrientsConsumedCurrentMonth();
        Map<String, Integer> monthlyGoal = analyticsService.getMonthlyCustomizedGoalStats();

        model.addAttribute("nutrientsConsumedThisWeek", nutrientsConsumedThisWeek);
        model.addAttribute("goals", weeklyGoal);

        model.addAttribute("nutrientsConsumedThisMonth", nutrientsConsumedThisMonth);
        model.addAttribute("mgoals", monthlyGoal);

        model.addAttribute("currentWeeklyProgressPercentage", analyticsService.percentageToGoal(nutrientsConsumedThisWeek, weeklyGoal));
        model.addAttribute("currentMonthlyProgressPercentage", analyticsService.percentageToGoal(nutrientsConsumedThisMonth, monthlyGoal));

        return "analytics.html";
    }

}
