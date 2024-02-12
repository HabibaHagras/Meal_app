package com.example.project_app.planMeals.view;

import com.example.project_app.model.Meal;
import com.example.project_app.model.MealPlan;

import java.util.List;

public interface AllPlanView {
    public void showdata(List<Meal> products);
    public void showErrorMsg(String error);
    public void deleteProductPlan(Meal mealPlan ,String day);

}
