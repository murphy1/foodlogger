package com.murphy1.foodlogger.services;

import com.murphy1.foodlogger.model.NutritionixDetailedProduct;
import com.murphy1.foodlogger.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getCurrentUser();

    NutritionixDetailedProduct addFood(NutritionixDetailedProduct food);
    List<NutritionixDetailedProduct> foodConsumedToday();
    void removeFood(String food);
    Map<String, Integer> dailyProgressToGoal();
    Map<String, Integer> customizedDailyGoals();
    Map<String, Double> dailyGoalProgressBar();
}
