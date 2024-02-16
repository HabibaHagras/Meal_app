package com.example.project_app.dp;

import androidx.lifecycle.LiveData;

import com.example.project_app.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealLocalDataSource {
    Completable insertMeal(Meal meal);
    void  deleteMeal(Meal meal);
    Flowable<List<Meal>> getAllStoredMeals();
    Flowable<List<Meal>> getAllStoredMealsForUser(String user);
    Completable insertMealPlan(Meal meal,String day);


}
