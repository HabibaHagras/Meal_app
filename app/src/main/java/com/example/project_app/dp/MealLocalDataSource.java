package com.example.project_app.dp;

import androidx.lifecycle.LiveData;

import com.example.project_app.model.Meal;

import java.util.List;

public interface MealLocalDataSource {
    void insertMeal(Meal meal);
    void  deleteMeal(Meal meal);
    LiveData<List<Meal>> getAllStoredMeals();
    void insertMealPlan(Meal meal,String day);


}
