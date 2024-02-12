package com.example.project_app.randomMeal.view;

import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.MealPlan;

import java.util.List;

public interface AllMealView {
    public void showdata(List<Meal>products);
    public void showErrorMsg(String error);
    public void addProduct(Meal product);
    public  void addProuductPlan(Meal mealPlan , String day );

}
