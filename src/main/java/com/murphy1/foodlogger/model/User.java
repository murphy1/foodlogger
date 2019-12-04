package com.murphy1.foodlogger.model;

import lombok.Builder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
@Builder
public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    @Transient
    private String emailCheck;
    private String password;
    @Transient
    private String passwordCheck;

    private boolean active;
    private String roles;
    private List<NutritionixDetailedProduct> foodList;
    private Map<String, Integer> goals;

    public User() {
    }

    public User(String id, String firstName, String lastName, String username, String email, String emailCheck, String password, String passwordCheck, boolean active, String roles, List<NutritionixDetailedProduct> foodList, Map<String, Integer> goals) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.emailCheck = emailCheck;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.active = active;
        this.roles = roles;
        this.foodList = foodList;
        this.goals = goals;
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

    public String getEmailCheck() {
        return emailCheck;
    }

    public void setEmailCheck(String emailCheck) {
        this.emailCheck = emailCheck;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
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

    public Map<String, Integer> getGoals() {
        return goals;
    }

    public void setGoals(Map<String, Integer> goals) {
        this.goals = goals;
    }
}
