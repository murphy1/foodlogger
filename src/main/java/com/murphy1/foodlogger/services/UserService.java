package com.murphy1.foodlogger.services;

import com.murphy1.foodlogger.model.NutritionixDetailedProduct;

import java.util.List;

public interface UserService {
    NutritionixDetailedProduct addFood(NutritionixDetailedProduct food);
    List<NutritionixDetailedProduct> foodConsumedToday();
}
