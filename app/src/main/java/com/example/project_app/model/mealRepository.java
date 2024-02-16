package com.example.project_app.model;

import androidx.lifecycle.LiveData;

import com.example.project_app.network.NetworkCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface mealRepository {
    public LiveData<List<Meal>> getStoredProduct();
    public void  insertMeal(Meal meal);
    public  void  deleteMeal(Meal meal);
    public Observable<List<Meal>> getAllMeals(NetworkCallback networkCallback);
    public Observable<List<Category>> getAllCategories(NetworkCallback networkCallback);
    public void  insertMeal(Meal meal,String DAY);
    public Observable<List<Meal>>  getAllMealsSearch(NetworkCallback networkCallback,String wordMeaL);
    public Observable<List<Meal>>  getAllMealsSearchCategory(NetworkCallback networkCallback, String wordCategory) ;
    public Observable<List<Meal>>  getAllMealsSearchIngredient(NetworkCallback networkCallback, String ingredient) ;
    public Observable<List<Area>>  getAllAreas(NetworkCallback networkCallback);
    public void getAllMealsSearchAreat(NetworkCallback networkCallback, String area) ;



}
