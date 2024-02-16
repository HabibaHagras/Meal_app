package com.example.project_app.dp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.project_app.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealLocalDataSourceIm implements MealLocalDataSource {
    private MealDAO dao;
    Flowable<List<Meal>> storedMeals;
    Flowable<List<Meal>> storedMealsForUser;
    Meal meal;
    String email;
    private static final String TAG = "MealLocalDataSourceIm";
//    SharedPreferences sp = getApplicationContext().getSharedPreferences("useremail", Context.MODE_PRIVATE);
//    String email= sp.getString("userEmail","");
    private static MealLocalDataSourceIm localDataSourceIm=null;
    public MealLocalDataSourceIm(Context context) {
        AppDataBase appDataBase=AppDataBase.getInstance(context.getApplicationContext());
//        SharedPreferences sp = context.getSharedPreferences("useremail", Context.MODE_PRIVATE);
//        email = sp.getString("userEmail", "");
        dao=appDataBase.getmealDAO();
        storedMeals=dao.getAllMeals();
        Log.i(TAG, "MealLocalDataSourceIm: "+"   "+email);
        storedMealsForUser=dao.getFavoriteMealsForUser("meal.getUserEmail()");

    }
public  static MealLocalDataSourceIm getInstance(Context context){
        if(localDataSourceIm==null){
            localDataSourceIm=new MealLocalDataSourceIm(context);
        }
        return localDataSourceIm;
}
    @Override
    public Completable insertMeal(Meal meal) {

       // Toast.makeText(AllProductsActivity.this,"added",Toast.LENGTH_SHORT).show();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//            dao.insert(meal);
//            }
//        }).start();
        return dao.insert(meal);


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
    public Flowable <List<Meal>> getAllStoredMeals() {
        return storedMeals;
    }

    @Override
    public Flowable<List<Meal>> getAllStoredMealsForUser(String user) {
        return storedMealsForUser;
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
