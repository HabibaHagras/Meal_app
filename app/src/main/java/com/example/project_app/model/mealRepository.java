package com.example.project_app.model;

import androidx.lifecycle.LiveData;

import com.example.project_app.network.NetworkCallback;

import java.util.List;

public interface mealRepository {
    public LiveData<List<Meal>> getStoredProduct();
    public void  insertMeal(Meal meal);
    public  void  deleteMeal(Meal meal);
    public void getAllMeals(NetworkCallback networkCallback);
    public void getAllCategories(NetworkCallback networkCallback);
    public LiveData<List<Meal>> getStoredProductPlan(String day_of_week);
    public void  insertMealPlan(Meal mealPlan,String day_of_week);
    public  void  deleteMealPlan(Meal mealPlan,String day_of_week);

}
