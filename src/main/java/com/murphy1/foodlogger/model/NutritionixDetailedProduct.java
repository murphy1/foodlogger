package com.murphy1.foodlogger.model;

import lombok.Builder;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@ToString
public class NutritionixDetailedProduct {

    private String foodName;
    private Integer servingQuantity;
    private String servingUnit;
    private Integer servingWeightGrams;
    private Integer calories;
    private Integer totalFat;
    private Integer saturatedFat;
    private Integer cholesterol;
    private Integer sodium;
    private Integer totalCarbs;
    private Integer dietaryFiber;
    private Integer sugars;
    private Integer protein;
    private int potassium;
    private LocalDate dateAdded;

    public NutritionixDetailedProduct() {

    }

    public NutritionixDetailedProduct(String foodName, Integer servingQuantity, String servingUnit, Integer servingWeightGrams, Integer calories, Integer totalFat, Integer saturatedFat, Integer cholesterol, Integer sodium, Integer totalCarbs, Integer dietaryFiber, Integer sugars, Integer protein, int potassium, LocalDate dateAdded) {
        this.foodName = foodName;
        this.servingQuantity = servingQuantity;
        this.servingUnit = servingUnit;
        this.servingWeightGrams = servingWeightGrams;
        this.calories = calories;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedFat;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.totalCarbs = totalCarbs;
        this.dietaryFiber = dietaryFiber;
        this.sugars = sugars;
        this.protein = protein;
        this.potassium = potassium;
        this.dateAdded = dateAdded;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getServingQuantity() {
        return servingQuantity;
    }

    public void setServingQuantity(Integer servingQuantity) {
        this.servingQuantity = servingQuantity;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    public Integer getServingWeightGrams() {
        return servingWeightGrams;
    }

    public void setServingWeightGrams(Integer servingWeightGrams) {
        this.servingWeightGrams = servingWeightGrams;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(Integer totalFat) {
        this.totalFat = totalFat;
    }

    public Integer getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(Integer saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public Integer getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Integer cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Integer getSodium() {
        return sodium;
    }

    public void setSodium(Integer sodium) {
        this.sodium = sodium;
    }

    public Integer getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(Integer totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public Integer getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(Integer dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public Integer getSugars() {
        return sugars;
    }

    public void setSugars(Integer sugars) {
        this.sugars = sugars;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public int getPotassium() {
        return potassium;
    }

    public void setPotassium(int potassium) {
        this.potassium = potassium;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
}
