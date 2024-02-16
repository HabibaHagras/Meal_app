package com.example.project_app.model;

import androidx.lifecycle.LiveData;

import com.example.project_app.network.NetworkCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface mealRepository {
    public Flowable<List<Meal>> getStoredProduct();
    public Flowable<List<Meal>> getStoredProductforUser(String user);
    public Completable insertMeal(Meal meal);
    public  void  deleteMeal(Meal meal);
    public Observable<List<Meal>> getAllMeals(NetworkCallback networkCallback);
    public Observable<List<Category>> getAllCategories(NetworkCallback networkCallback);
    public Completable  insertMeal(Meal meal,String DAY);
    public Observable<List<Meal>>  getAllMealsSearch(NetworkCallback networkCallback,String wordMeaL);
    public Observable<List<Meal>>  getAllMealsSearchCategory(NetworkCallback networkCallback, String wordCategory) ;
    public Observable<List<Meal>>  getAllMealsSearchIngredient(NetworkCallback networkCallback, String ingredient) ;
    public Observable<List<Area>>  getAllAreas(NetworkCallback networkCallback);
    public void getAllMealsSearchAreat(NetworkCallback networkCallback, String area) ;



}
