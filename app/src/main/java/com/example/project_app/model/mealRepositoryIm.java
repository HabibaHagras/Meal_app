package com.example.project_app.model;

import androidx.lifecycle.LiveData;

import com.example.project_app.dp.MealLocalDataSource;
import com.example.project_app.network.MealRemoteDataSource;
import com.example.project_app.network.NetworkCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

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
    public Flowable<List<Meal>> getStoredProduct() {
        return localDataSource.getAllStoredMeals();
    }

    @Override
    public Flowable<List<Meal>> getStoredProductforUser(String user) {
        return localDataSource.getAllStoredMealsForUser(user);
    }

    @Override
    public Completable insertMeal(Meal meal) {
       return localDataSource.insertMeal(meal);

    }

    @Override
    public void deleteMeal(Meal meal) {
        localDataSource.deleteMeal(meal);

    }

    @Override
    public Observable<List<Meal>> getAllMeals(NetworkCallback networkCallback) {
      return   remoteDataSource.makeNetwokCall(networkCallback);
    }

    @Override
    public Observable<List<Category>> getAllCategories(NetworkCallback networkCallback) {
           return    remoteDataSource.makeNetwokCallCategory(networkCallback);

    }

    @Override
    public Completable insertMeal(Meal meal, String DAY) {
        return localDataSource.insertMealPlan(meal,DAY);

    }

    @Override
    public Observable<List<Meal>> getAllMealsSearch(NetworkCallback networkCallback, String wordMeaL) {
      return  remoteDataSource.makeNetwokCallSearch(networkCallback,wordMeaL);
    }
    @Override
    public Observable<List<Meal>> getAllMealsSearchCategory(NetworkCallback networkCallback, String wordCategory) {
      return  remoteDataSource.makeNetwokCallSearchCategory(networkCallback,wordCategory);
    }

    @Override
    public Observable<List<Meal>> getAllMealsSearchIngredient(NetworkCallback networkCallback, String ingredient) {
       return remoteDataSource.makeNetwokCallSearchIngredient(networkCallback,ingredient);
    }

    @Override
    public Observable<List<Area>>  getAllAreas(NetworkCallback networkCallback) {
       return remoteDataSource.makeNetwokCallArea(networkCallback);
    }

    @Override
    public Observable<List<Meal>> getAllMealsSearchArea(NetworkCallback networkCallback, String area) {
       return remoteDataSource.makeNetwokCallSearchArea(networkCallback,area);
    }

}
