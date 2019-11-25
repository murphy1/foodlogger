package com.murphy1.foodlogger.services;

import com.murphy1.foodlogger.model.NutritionixBaseProduct;
import com.murphy1.foodlogger.model.NutritionixDetailedProduct;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface NutritionixService {
    List<NutritionixBaseProduct> searchQuery(String query) throws UnsupportedEncodingException;
    NutritionixDetailedProduct getDetailedProduct(String query) throws UnsupportedEncodingException;
}
