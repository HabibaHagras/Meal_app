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
    public void  insertMeal(Meal meal,String DAY);
    public void getAllMealsSearch(NetworkCallback networkCallback,String wordMeaL);
    public void getAllMealsSearchCategory(NetworkCallback networkCallback, String wordCategory) ;
    public void getAllMealsSearchIngredient(NetworkCallback networkCallback, String ingredient) ;
    public void getAllAreas(NetworkCallback networkCallback);



}
