package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.model.NutritionixBaseProduct;
import com.murphy1.foodlogger.model.NutritionixDetailedProduct;
import com.murphy1.foodlogger.services.NutritionixService;
import com.murphy1.foodlogger.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
public class NutritionixController {

    // Placeholder to help save the product to the users profile
    private NutritionixDetailedProduct mostRecentProduct = null;

    private NutritionixService nutritionixService;
    private UserService userService;

    public NutritionixController(NutritionixService nutritionixService, UserService userService) {
        this.nutritionixService = nutritionixService;
        this.userService = userService;
    }

    @GetMapping("/logger/product_query")
    public String queryNutritionixApi(Model model, @ModelAttribute NutritionixBaseProduct nutritionixBaseProduct) throws UnsupportedEncodingException {
        model.addAttribute("baseProductList", nutritionixService.searchQuery(nutritionixBaseProduct.getFood_name()));

        return "baseproductlist.html";
    }

    @GetMapping("/logger/product_query/detailed_query/{query}")
    public String getDetailedProduct(Model model, @PathVariable String query) throws UnsupportedEncodingException {
        // Placeholder to help save the product to the users profile
        mostRecentProduct = nutritionixService.getDetailedProduct(query);

        model.addAttribute("detailedProductInfo", mostRecentProduct);

        return "detailedproduct.html";
    }

    @GetMapping("/add_food")
    public String addFood(Model model){
        model.addAttribute("foodList", userService.addFood(mostRecentProduct));

        return "redirect:/profile";
    }

}
