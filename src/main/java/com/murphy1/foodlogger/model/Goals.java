package com.murphy1.foodlogger.model;

public class Goals {

    private Integer calories;
    private Integer sugars;
    private Integer fat;
    private Integer sodium;
    private Integer fiber;
    private Integer protein;
    private Integer potassium;
    private Integer carbs;

    public Goals(){

    }

    public Goals(Integer calories, Integer sugars, Integer fat, Integer sodium, Integer fiber, Integer protein, Integer potassium, Integer carbs) {
        this.calories = calories;
        this.sugars = sugars;
        this.fat = fat;
        this.sodium = sodium;
        this.fiber = fiber;
        this.protein = protein;
        this.potassium = potassium;
        this.carbs = carbs;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getSugars() {
        return sugars;
    }

    public void setSugars(Integer sugars) {
        this.sugars = sugars;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getSodium() {
        return sodium;
    }

    public void setSodium(Integer sodium) {
        this.sodium = sodium;
    }

    public Integer getFiber() {
        return fiber;
    }

    public void setFiber(Integer fiber) {
        this.fiber = fiber;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public Integer getPotassium() {
        return potassium;
    }

    public void setPotassium(Integer potassium) {
        this.potassium = potassium;
    }

    public Integer getCarbs() {
        return carbs;
    }

    public void setCarbs(Integer carbs) {
        this.carbs = carbs;
    }
}
