package com.example.project_app.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealPlanDAO;

import java.util.List;

public class Repository {
    private Context context;
    private LiveData<List<Meal>> storedMeal;
    private LiveData<List<MealPlan>> storedMealPlan;
    private static Repository repo =null;
    AppDataBase dp;
    MealDAO DAO;
    String dayofweek;
    MealPlanDAO mealPlanDAO;
    private Repository(Context context){
        this.context=context;
        dp= AppDataBase.getInstance(context.getApplicationContext());
        DAO=dp.getmealDAO();
        mealPlanDAO=dp.getmealPlanDAO();
        storedMeal=DAO.getAllMeals();
        storedMealPlan=mealPlanDAO.getAllMealsPlan(dayofweek);

    }
public static Repository getInstance(Context context){
        if (repo==null){
            repo =new Repository(context);
        }
        return repo;
}
public  LiveData<List<Meal>> getStoredMeal(){
        return storedMeal;
}
public  void delete(Meal meal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DAO.delete(meal);
            }
        });

}

}
