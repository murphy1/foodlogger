package com.murphy1.foodlogger.services;

import java.util.Map;

public interface AnalyticsService {
    Map<String, Integer> nutrientsConsumedCurrentWeek();
    Map<String, Integer> nutrientsConsumedCurrentMonth();

    Map<String, Integer> getWeeklyCustomizedGoalStats();
    Map<String, Integer> getMonthlyCustomizedGoalStats();

    Map<String, Double> percentageToGoal(Map<String, Integer> currentProgress, Map<String, Integer> goal);
}
