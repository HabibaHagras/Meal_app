package com.example.project_app.dp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.project_app.model.Meal;

@Database(entities = {Meal.class},version = 1)

public  abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance =null;
    public abstract MealDAO getmealDAO();

    public static  synchronized  AppDataBase getInstance(Context context) {
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,"Mealdb").build();
        }
        return instance;
    }
}
