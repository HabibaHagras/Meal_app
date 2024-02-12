package com.example.project_app.dp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project_app.model.MealPlan;

import java.util.List;

@Dao
public interface MealPlanDAO {
    @Query("SELECT * FROM plan_table WHERE dayOfWeek = :day_of_week")
    LiveData<List<MealPlan>> getAllMealsPlan(String day_of_week);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPlan(MealPlan mealPlanal);
    @Delete
    void deletePlan (MealPlan mealPlanal);
}
