package com.example.project_app.model;

import java.util.List;

public class mealResponse {
    List<Meal> meals;
    List<MealPlan>mealPlans;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setProducts(List<Meal> products) {
        this.meals = products;
    }
    public List<MealPlan> getMealsPlan() {
        return mealPlans;
    }

}
