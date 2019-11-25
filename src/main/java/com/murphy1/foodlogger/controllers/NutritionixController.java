package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.model.NutritionixBaseProduct;
import com.murphy1.foodlogger.services.NutritionixService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
public class NutritionixController {

    private NutritionixService nutritionixService;

    public NutritionixController(NutritionixService nutritionixService) {
        this.nutritionixService = nutritionixService;
    }

    @GetMapping("/logger/product_query")
    public String queryNutritionixApi(Model model, @ModelAttribute NutritionixBaseProduct nutritionixBaseProduct) throws UnsupportedEncodingException {
        model.addAttribute("baseProductList", nutritionixService.searchQuery(nutritionixBaseProduct.getFood_name()));

        return "baseproductlist.html";
    }

    @GetMapping("/logger/product_query/detailed_query/{query}")
    public String getDetailedProduct(Model model, @PathVariable String query) throws UnsupportedEncodingException {
        model.addAttribute("detailedProductInfo", nutritionixService.getDetailedProduct(query));

        return "detailedproduct.html";
    }

}
