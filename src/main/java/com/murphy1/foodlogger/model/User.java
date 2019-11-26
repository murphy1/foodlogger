package com.murphy1.foodlogger.model;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Builder
public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private boolean active;
    private String roles;
    private List<NutritionixDetailedProduct> foodList;

    public User(String id, String firstName, String lastName, String username, String email, String password, boolean active, String roles, List<NutritionixDetailedProduct> foodList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.foodList = foodList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<NutritionixDetailedProduct> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<NutritionixDetailedProduct> foodList) {
        this.foodList = foodList;
    }
}
