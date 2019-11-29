package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.services.AnalyticsService;
import com.murphy1.foodlogger.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("nutrientsConsumedThisWeek", analyticsService.nutrientsConsumedCurrentWeek());
        model.addAttribute("goals", analyticsService.getWeeklyCustomizedGoalStats());

        model.addAttribute("nutrientsConsumedThisMonth", analyticsService.nutrientsConsumedCurrentMonth());
        model.addAttribute("mgoals", analyticsService.getMonthlyCustomizedGoalStats());

        return "analytics.html";
    }

}
