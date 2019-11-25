package com.murphy1.foodlogger.model;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class NutritionixBaseProduct {

    private String food_name;
    private String serving_unit;
    private String tag_name;
    private Integer serving_qty;
    private Integer tag_id;
    private String locale;

    public NutritionixBaseProduct(){

    }

    public NutritionixBaseProduct(String food_name, String serving_unit, String tag_name, Integer serving_qty, Integer tag_id, String locale) {
        this.food_name = food_name;
        this.serving_unit = serving_unit;
        this.tag_name = tag_name;
        this.serving_qty = serving_qty;
        this.tag_id = tag_id;
        this.locale = locale;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getServing_unit() {
        return serving_unit;
    }

    public void setServing_unit(String serving_unit) {
        this.serving_unit = serving_unit;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public Integer getServing_qty() {
        return serving_qty;
    }

    public void setServing_qty(Integer serving_qty) {
        this.serving_qty = serving_qty;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
