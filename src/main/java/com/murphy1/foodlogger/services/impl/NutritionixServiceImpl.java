package com.murphy1.foodlogger.services.impl;

import com.murphy1.foodlogger.model.NutritionixBaseProduct;
import com.murphy1.foodlogger.model.NutritionixDetailedProduct;
import com.murphy1.foodlogger.services.NutritionixService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.json.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NutritionixServiceImpl implements NutritionixService {

    private String nutritionixInstantSearch = "https://trackapi.nutritionix.com/v2/search/instant?query=";
    private String nutritionixNutrientsSearch = "https://trackapi.nutritionix.com/v2/natural/nutrients";

    // Headers
    private String contentType = "application/json";
    private String appId = "";
    private String appKey = "";

    @Override
    public List<NutritionixBaseProduct> searchQuery(String query){
        //replace spaces in the query with '+'
        String[] strArray = query.split(" ");
        String newQuery = "";

        if (strArray.length == 1){
            newQuery = query;
        }else{
            newQuery = strArray[0];

            for (int i = 1; i < strArray.length; i++){
                newQuery = newQuery.concat("+"+strArray[i]);
            }
        }

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(nutritionixInstantSearch + newQuery);
        getRequest.setHeader("Content-Type", contentType);
        getRequest.setHeader("x-app-id", appId);
        getRequest.setHeader("x-app-key", appKey);

        String str = "";

        try{
            HttpResponse response = httpClient.execute(getRequest);
            HttpEntity entity = response.getEntity();
            str = EntityUtils.toString(entity);
        }catch (IOException e){
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(str);
        JSONArray common = (JSONArray) jsonObject.get("common");

        List<NutritionixBaseProduct> productList = new ArrayList<>();

        // only return top 10 searches to maintain speed
        for (int i = 0; i < 10; i++){
            NutritionixBaseProduct product = new NutritionixBaseProduct();
            try{
                product.setFood_name(common.getJSONObject(i).getString("food_name"));
                product.setServing_unit(common.getJSONObject(i).getString("serving_unit"));
                product.setTag_name(common.getJSONObject(i).getString("tag_name"));
                product.setServing_qty(common.getJSONObject(i).getInt("serving_qty"));
                product.setTag_id(common.getJSONObject(i).getInt("tag_id"));
                productList.add(product);
            }catch (JSONException e){
                e.printStackTrace();
                log.info("-----");
                log.info("-----");
                log.info("Error thrown as one field was returned as null from the Nutritionix API!");
            }
        }

        return productList;
    }

    @Override
    public NutritionixDetailedProduct getDetailedProduct(String query) throws UnsupportedEncodingException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(nutritionixNutrientsSearch);

        String s = "query";
        String body = "{\""+s+"\" : \""+query+"\"}";
        HttpEntity entity = new ByteArrayEntity(body.getBytes("UTF-8"));
        postRequest.setEntity(entity);

        postRequest.setHeader("Content-Type", contentType);
        postRequest.setHeader("x-app-id", appId);
        postRequest.setHeader("x-app-key", appKey);

        String responseEntityString = "";

        try{
            HttpResponse response = httpClient.execute(postRequest);
            HttpEntity responseEntity = response.getEntity();
            responseEntityString = EntityUtils.toString(responseEntity);
        }catch (IOException e){
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(responseEntityString);
        JSONArray foods = (JSONArray) jsonObject.get("foods");

        NutritionixDetailedProduct product = new NutritionixDetailedProduct();

        try{
            product.setFoodName(foods.getJSONObject(0).getString("food_name"));
            product.setServingQuantity(foods.getJSONObject(0).getInt("serving_qty"));
            product.setServingUnit(foods.getJSONObject(0).getString("serving_unit"));
            product.setServingWeightGrams(foods.getJSONObject(0).getInt("serving_weight_grams"));
            product.setCalories(foods.getJSONObject(0).getInt("nf_calories"));
            product.setTotalFat(foods.getJSONObject(0).getInt("nf_total_fat"));
            product.setSaturatedFat(foods.getJSONObject(0).getInt("nf_saturated_fat"));
            product.setCholesterol(foods.getJSONObject(0).getInt("nf_cholesterol"));
            product.setSodium(foods.getJSONObject(0).getInt("nf_sodium"));
            product.setTotalCarbs(foods.getJSONObject(0).getInt("nf_total_carbohydrate"));
            product.setDietaryFiber(foods.getJSONObject(0).getInt("nf_dietary_fiber"));
            product.setSugars(foods.getJSONObject(0).getInt("nf_sugars"));
            product.setProtein(foods.getJSONObject(0).getInt("nf_protein"));
            product.setPotassium(foods.getJSONObject(0).getInt("nf_potassium"));
        }catch (JSONException e){
            e.printStackTrace();
            log.info("-----");
            log.info("-----");
            log.info("Error thrown as one field was returned as null from the Nutritionix API!");
        }

        return product;
    }
}