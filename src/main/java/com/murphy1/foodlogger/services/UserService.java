package com.murphy1.foodlogger.services;

import com.murphy1.foodlogger.model.NutritionixDetailedProduct;

import java.util.List;
import java.util.Map;

public interface UserService {
    NutritionixDetailedProduct addFood(NutritionixDetailedProduct food);
    List<NutritionixDetailedProduct> foodConsumedToday();
    void removeFood(String food);
    Map<String, Integer> getGoalDetails();
}
