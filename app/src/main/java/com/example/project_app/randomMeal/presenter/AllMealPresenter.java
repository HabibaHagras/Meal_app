package com.example.project_app.randomMeal.presenter;

import com.example.project_app.model.Meal;
import com.example.project_app.model.MealPlan;

public interface AllMealPresenter {
    public void getMeal();
    public void addtoFav(Meal meal);
    public void getCtegory();
    public  void addtoPlan(Meal mealPlan, String day);
}
