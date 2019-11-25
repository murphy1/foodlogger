package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.model.NutritionixBaseProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/home", "/homepage"})
    public String homepage(){

        return "index.html";
    }

    @GetMapping("/nutritionix")
    public String searchNutritionix(Model model){
        model.addAttribute("NutritionixBaseProduct", new NutritionixBaseProduct());

        return "nutritionix.html";
    }

}
