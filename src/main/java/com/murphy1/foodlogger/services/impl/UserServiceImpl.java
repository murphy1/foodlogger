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
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
