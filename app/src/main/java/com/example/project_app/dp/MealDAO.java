package com.example.project_app.dp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.project_app.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDAO {
    @Query("SELECT * From meal_table")
    Flowable<List<Meal>> getAllMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(Meal meal);

    @Delete
    void delete (Meal meal);
    @Query("SELECT * FROM meal_table WHERE userEmail = :userEmail")
    Flowable<List<Meal>> getFavoriteMealsForUser(String userEmail);


}
