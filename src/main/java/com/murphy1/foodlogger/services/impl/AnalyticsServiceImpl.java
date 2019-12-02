package com.murphy1.foodlogger.services.impl;

import com.murphy1.foodlogger.model.NutritionixDetailedProduct;
import com.murphy1.foodlogger.model.User;
import com.murphy1.foodlogger.services.AnalyticsService;
import com.murphy1.foodlogger.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private UserService userService;

    public AnalyticsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Map<String, Integer> nutrientsConsumedCurrentWeek() {
        User currentUser = userService.getCurrentUser();

        if (currentUser.getFoodList() == null){
            Map<String, Integer> emptyMap = new HashMap<>();
            return emptyMap;
        }
        List<NutritionixDetailedProduct> foodList = currentUser.getFoodList();

        // Get date range between start of last week and today
        LocalDate today = LocalDate.now();
        LocalDate startOfLastWeek = today.minusWeeks(1);

        // Get food consumed last week
        List<NutritionixDetailedProduct> foodConsumedLastWeek = foodList.stream()
                .filter(food -> food.getDateAdded().isAfter(startOfLastWeek))
                .collect(Collectors.toList());

        return countNutrientsHelper(foodConsumedLastWeek);
    }

    @Override
    public Map<String, Integer> nutrientsConsumedCurrentMonth() {
        User currentUser = userService.getCurrentUser();

        if (currentUser.getFoodList() == null){
            Map<String, Integer> emptyMap = new HashMap<>();
            return emptyMap;
        }
        List<NutritionixDetailedProduct> foodList = currentUser.getFoodList();

        // Get date range between start of last week and today
        LocalDate today = LocalDate.now();
        LocalDate startOfLastMonth = today.minusMonths(1);

        // Get food consumed last week
        List<NutritionixDetailedProduct> foodConsumedLastMonth = foodList.stream()
                .filter(food -> food.getDateAdded().isAfter(startOfLastMonth))
                .collect(Collectors.toList());

        return countNutrientsHelper(foodConsumedLastMonth);
    }

    @Override
    public Map<String, Integer> getWeeklyCustomizedGoalStats() {

        // This method will multiply daily goals by seven to get the weekly goals

        Map<String, Integer> dailyGoals = userService.getCurrentUser().getGoals();

        // Loop through each key and update the value
        for (String key : dailyGoals.keySet()){
            Integer valueToReplace = dailyGoals.get(key);
            dailyGoals.replace(key, valueToReplace, valueToReplace * 7);
        }
        return dailyGoals;
    }

    @Override
    public Map<String, Integer> getMonthlyCustomizedGoalStats() {

        // This method will multiply daily goals by 30 to get the monthly goals

        Map<String, Integer> dailyGoals = userService.getCurrentUser().getGoals();

        // Loop through each key and update the value
        for (String key : dailyGoals.keySet()){
            Integer valueToReplace = dailyGoals.get(key);
            dailyGoals.replace(key, valueToReplace, valueToReplace * getMonth());
        }
        return dailyGoals;
    }

    private Map<String, Integer> countNutrientsHelper(List<NutritionixDetailedProduct> consumedFoods){

        // Helper method to count nutrients from a given list of foods

        Map<String, Integer> returnMap = new HashMap<>();
        Integer calories = 0;
        Integer sugars = 0;
        Integer fat = 0;
        Integer sodium = 0;
        Integer fiber = 0;
        Integer protein = 0;
        Integer potassium = 0;
        Integer carbs = 0;

        for (NutritionixDetailedProduct foodItem : consumedFoods){
            if (foodItem.getCalories() != null){
                calories = calories + foodItem.getCalories();
            }
            if (foodItem.getSugars() != null){
                sugars = sugars + foodItem.getSugars();
            }
            if (foodItem.getTotalFat() != null){
                fat = fat + foodItem.getTotalFat();
            }
            if (foodItem.getSodium() != null){
                sodium = sodium + foodItem.getSodium();
            }
            if (foodItem.getDietaryFiber() != null){
                fiber = fiber + foodItem.getDietaryFiber();
            }
            if (foodItem.getProtein() != null){
                protein = protein + foodItem.getProtein();
            }
            if (foodItem.getPotassium() != null){
                potassium = potassium + foodItem.getPotassium();
            }
            if (foodItem.getTotalCarbs() != null){
                carbs = carbs + foodItem.getTotalCarbs();
            }
        }

        returnMap.put("Calories", calories);
        returnMap.put("Sugars", sugars);
        returnMap.put("Fat", fat);
        returnMap.put("Sodium", sodium);
        returnMap.put("Fiber", fiber);
        returnMap.put("Protein", protein);
        returnMap.put("Potassium", potassium);
        returnMap.put("Carbs", carbs);

        return returnMap;
    }

    private Integer getMonth(){

        // will get the amount of days in the current month. For monthly stats calculations

        return LocalDate.now().lengthOfMonth();
    }

}
