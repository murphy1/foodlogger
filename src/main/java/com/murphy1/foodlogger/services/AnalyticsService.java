package com.murphy1.foodlogger.services;

import java.util.Map;

public interface AnalyticsService {
    Map<String, Integer> nutrientsConsumedCurrentWeek();
    Map<String, Integer> nutrientsConsumedCurrentMonth();

    Map<String, String> getWeeklyCustomizedGoalStats();
    Map<String, String> getMonthlyCustomizedGoalStats();
}
