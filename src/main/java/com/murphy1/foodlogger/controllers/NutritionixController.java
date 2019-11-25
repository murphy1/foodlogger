package com.murphy1.foodlogger.controllers;

import com.murphy1.foodlogger.model.NutritionixBaseProduct;
import com.murphy1.foodlogger.model.NutritionixDetailedProduct;
import com.murphy1.foodlogger.services.NutritionixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class NutritionixController {

    private NutritionixService nutritionixService;

    public NutritionixController(NutritionixService nutritionixService) {
        this.nutritionixService = nutritionixService;
    }

    @GetMapping("/logger/product_query/{query}")
    public List<NutritionixBaseProduct> queryNutritionixApi(@PathVariable String query) throws UnsupportedEncodingException {
        List<NutritionixBaseProduct> list = nutritionixService.searchQuery(query);

        for (NutritionixBaseProduct obj : list){
            System.out.println(obj.toString());
        }

        return list;
    }

    @GetMapping("/logger/product_query/detailed_query/{query}")
    public NutritionixDetailedProduct getDetailedProduct(@PathVariable String query) throws UnsupportedEncodingException {
        NutritionixDetailedProduct product = nutritionixService.getDetailedProduct(query);

        System.out.println(product.toString());

        return product;
    }

}
