package com.murphy1.foodlogger.services.impl;

import com.murphy1.foodlogger.model.NutritionixDetailedProduct;
import com.murphy1.foodlogger.model.User;
import com.murphy1.foodlogger.repositories.UserRepository;
import com.murphy1.foodlogger.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        User currentUser = userRepository.findUserByUsername(((UserDetails)principal).getUsername()).block();

        return currentUser;
    }

    @Override
    public NutritionixDetailedProduct addFood(NutritionixDetailedProduct food) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        User currentUser = userRepository.findUserByUsername(((UserDetails)principal).getUsername()).block();
        List<NutritionixDetailedProduct> foodList = currentUser.getFoodList();

        if (foodList == null){
            foodList = new ArrayList<>();
        }

        food.setDateAdded(LocalDate.now());
        foodList.add(food);

        currentUser.setFoodList(foodList);

        userRepository.save(currentUser).block();

        return food;
    }

    @Override
    public List<NutritionixDetailedProduct> foodConsumedToday() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        User currentUser = userRepository.findUserByUsername(((UserDetails)principal).getUsername()).block();
        if (currentUser.getFoodList() == null){
            List<NutritionixDetailedProduct> list = new ArrayList<>();
            return list;
        }
        List<NutritionixDetailedProduct> foodList = currentUser.getFoodList();
        List<NutritionixDetailedProduct> listToReturn = foodList.stream()
                .filter(food -> food.getDateAdded().isBefore(LocalDate.now().plusDays(1)))
                .filter(food -> food.getDateAdded().isAfter(LocalDate.now().minusDays(1)))
                .collect(Collectors.toList());

        return listToReturn;
    }

    @Override
    public void removeFood(String food) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        User currentUser = userRepository.findUserByUsername(((UserDetails)principal).getUsername()).block();
        List<NutritionixDetailedProduct> foodList = currentUser.getFoodList();

        NutritionixDetailedProduct productToRemove = foodList.stream()
                .filter(product -> product.getFoodName().equals(food))
                .findFirst().get();

        foodList.remove(productToRemove);

        currentUser.setFoodList(foodList);
        userRepository.save(currentUser).block();
    }

    @Override
    public Map<String, Integer> dailyProgressToGoal() {
        List<NutritionixDetailedProduct> foodList = foodConsumedToday();

        Map<String, Integer> goalDetails = new HashMap<>();
        Integer calories = 0;
        Integer sugars = 0;
        Integer fat = 0;
        Integer sodium = 0;
        Integer fiber = 0;
        Integer protein = 0;
        Integer potassium = 0;
        Integer carbs = 0;

        for (NutritionixDetailedProduct foodItem : foodList){
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

        goalDetails.put("Calories", calories);
        goalDetails.put("Sugars", sugars);
        goalDetails.put("Fat", fat);
        goalDetails.put("Sodium", sodium);
        goalDetails.put("Fiber", fiber);
        goalDetails.put("Protein", protein);
        goalDetails.put("Potassium" , potassium);
        goalDetails.put("Carbs", carbs);

        return goalDetails;
    }

    @Override
    public Map<String, String> customizedDailyGoals() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        User currentUser = userRepository.findUserByUsername(((UserDetails)principal).getUsername()).block();

        Map<String, String> returnMap = new HashMap<>();

        if (currentUser.getGoals() != null){
            returnMap = currentUser.getGoals();
        }
        else{
            // Recommended Values set by World Health Org
            returnMap.put("Calories", "/2500kcal");
            returnMap.put("Sugars", "/90g");
            returnMap.put("Fat", "/70g");
            returnMap.put("Sodium", "/2300mg");
            returnMap.put("Fiber", "/30g");
            returnMap.put("Protein", "/55g");
            returnMap.put("Potassium", "/3500mg");
            returnMap.put("Carbs", "/310g");
        }

        return returnMap;
    }
}
