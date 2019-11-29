package com.murphy1.foodlogger.model;

public class Goals {

    private String calories;
    private String sugars;
    private String fat;
    private String sodium;
    private String fiber;
    private String protein;
    private String potassium;
    private String carbs;

    public Goals(){

    }

    public Goals(String calories, String sugars, String fat, String sodium, String fiber, String protein, String potassium, String carbs) {
        this.calories = calories;
        this.sugars = sugars;
        this.fat = fat;
        this.sodium = sodium;
        this.fiber = fiber;
        this.protein = protein;
        this.potassium = potassium;
        this.carbs = carbs;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getSugars() {
        return sugars;
    }

    public void setSugars(String sugars) {
        this.sugars = sugars;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getSodium() {
        return sodium;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    public String getFiber() {
        return fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getPotassium() {
        return potassium;
    }

    public void setPotassium(String potassium) {
        this.potassium = potassium;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }
}
