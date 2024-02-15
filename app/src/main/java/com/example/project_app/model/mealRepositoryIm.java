package com.example.project_app.model;

import androidx.lifecycle.LiveData;

import com.example.project_app.dp.MealLocalDataSource;
import com.example.project_app.network.MealRemoteDataSource;
import com.example.project_app.network.NetworkCallback;

import java.util.List;

public class mealRepositoryIm implements mealRepository {
    MealRemoteDataSource remoteDataSource;


    MealLocalDataSource localDataSource;
    private  static mealRepositoryIm repo =null;


    public mealRepositoryIm(MealRemoteDataSource remoteDataSource, MealLocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public static mealRepositoryIm getInstance(MealRemoteDataSource MealRemoteDataSource , MealLocalDataSource MealLocalDataSource){
        if (repo==null){
            repo=new mealRepositoryIm(MealRemoteDataSource,MealLocalDataSource);
        }
        return  repo;
    }

    @Override
    public LiveData<List<Meal>> getStoredProduct() {
        return localDataSource.getAllStoredMeals();
    }

    @Override
    public void insertMeal(Meal meal) {
        localDataSource.insertMeal(meal);

    }

    @Override
    public void deleteMeal(Meal meal) {
        localDataSource.deleteMeal(meal);

    }

    @Override
    public void getAllMeals(NetworkCallback networkCallback) {
        remoteDataSource.makeNetwokCall(networkCallback);
    }

    @Override
    public void getAllCategories(NetworkCallback networkCallback) {
               remoteDataSource.makeNetwokCallCategory(networkCallback);

    }

    @Override
    public void insertMeal(Meal meal, String DAY) {
        localDataSource.insertMealPlan(meal,DAY);

    }

    @Override
    public void getAllMealsSearch(NetworkCallback networkCallback, String wordMeaL) {
        remoteDataSource.makeNetwokCallSearch(networkCallback,wordMeaL);
    }
    @Override
    public void getAllMealsSearchCategory(NetworkCallback networkCallback, String wordCategory) {
        remoteDataSource.makeNetwokCallSearchCategory(networkCallback,wordCategory);
    }

    @Override
    public void getAllMealsSearchIngredient(NetworkCallback networkCallback, String ingredient) {
        remoteDataSource.makeNetwokCallSearchIngredient(networkCallback,ingredient);
    }

    @Override
    public void getAllAreas(NetworkCallback networkCallback) {
        remoteDataSource.makeNetwokCallArea(networkCallback);
    }

    @Override
    public void getAllMealsSearchAreat(NetworkCallback networkCallback, String area) {
        remoteDataSource.makeNetwokCallSearchArea(networkCallback,area);
    }

}
