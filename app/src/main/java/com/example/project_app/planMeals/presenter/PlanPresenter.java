package com.example.project_app.planMeals.presenter;

import com.example.project_app.model.Meal;
import com.example.project_app.model.MealPlan;

public interface PlanPresenter {
    public void getMealPlan(String day);
    public void deleteproductPlan(Meal mealPlan ,String day);
}
