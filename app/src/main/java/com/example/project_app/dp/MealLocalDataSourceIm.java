package com.example.project_app.dp;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.project_app.model.Meal;

import java.util.List;

public class MealLocalDataSourceIm implements MealLocalDataSource {
    private MealDAO dao;
    LiveData<List<Meal>>storedMeals;
    private static MealLocalDataSourceIm localDataSourceIm=null;
    public MealLocalDataSourceIm(Context context) {
        AppDataBase appDataBase=AppDataBase.getInstance(context.getApplicationContext());
        dao=appDataBase.getmealDAO();
        storedMeals=dao.getAllMeals();

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
    public void insertMealPlan(Meal meal, String day) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insert(meal);
            }
        }).start();

    }
}
