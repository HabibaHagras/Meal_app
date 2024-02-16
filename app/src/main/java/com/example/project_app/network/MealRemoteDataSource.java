package com.example.project_app.network;

import com.example.project_app.model.Area;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface MealRemoteDataSource {
    Observable<List<Meal>> makeNetwokCall(NetworkCallback networkCallback);
    Observable<List<Category>>  makeNetwokCallCategory(NetworkCallback networkCallback);
    Observable<List<Meal>> makeNetwokCallSearch(NetworkCallback networkCallback,String wordMeal);
    Observable<List<Meal>> makeNetwokCallSearchCategory(NetworkCallback networkCallback,String wordCategory);
    Observable<List<Meal>>  makeNetwokCallSearchIngredient(NetworkCallback networkCallback,String ingredient);
    Observable<List<Meal>> makeNetwokCallSearchArea(NetworkCallback networkCallback,String area);
    Observable<List<Area>>  makeNetwokCallArea(NetworkCallback networkCallback);

}
