package com.example.project_app.dp;

import androidx.lifecycle.LiveData;

import com.example.project_app.model.Meal;
import com.example.project_app.model.MealPlan;

import java.util.List;

public interface MealLocalDataSource {
    void insertMeal(Meal meal);
    void  deleteMeal(Meal meal);
    LiveData<List<Meal>> getAllStoredMeals();
    void insertMealPlan(Meal mealPlan,String day_of_week);
    void  deleteMealPlan(Meal mealPlan,String day_of_week);
    LiveData<List<Meal>> getAllStoredMealsPlan(String day_of_week);
}
