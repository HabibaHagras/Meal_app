package com.example.project_app.dp;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.project_app.model.Meal;
import com.example.project_app.model.MealPlan;

import java.util.List;

public class MealLocalDataSourceIm implements MealLocalDataSource {
    private MealDAO dao;
    private MealPlanDAO mealDAO;
    LiveData<List<Meal>>storedMeals;
    LiveData<List<Meal>>storedMealsPlan;
    String dayofweek;
    private static MealLocalDataSourceIm localDataSourceIm=null;
    public MealLocalDataSourceIm(Context context) {
        AppDataBase appDataBase=AppDataBase.getInstance(context.getApplicationContext());
        dao=appDataBase.getmealDAO();
        mealDAO=appDataBase.getmealPlanDAO();
        storedMeals=dao.getAllMeals();
        storedMealsPlan=dao.getAllMealsPlan(dayofweek);

    }
public  static MealLocalDataSourceIm getInstance(Context context){
        if(localDataSourceIm==null){
            localDataSourceIm=new MealLocalDataSourceIm(context);
        }
        return localDataSourceIm;
}
    @Override
    public void insertMeal(Meal meal) {

       // Toast.makeText(AllProductsActivity.this,"added",Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
            dao.insert(meal);
            }
        }).start();

    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.delete(meal);
            }
        }).start();

    }

    @Override
    public LiveData<List<Meal>> getAllStoredMeals() {
        return storedMeals;
    }

    @Override
    public void insertMealPlan(Meal mealPlan, String day_of_week) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insertPlan(mealPlan);
            }
        }).start();
    }

    @Override
    public void deleteMealPlan(Meal mealPlan, String day_of_week) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.deletePlan(mealPlan);
            }
        }).start();

    }

    @Override
    public LiveData<List<Meal>> getAllStoredMealsPlan(String day_of_week) {
        return storedMealsPlan;
    }
}
