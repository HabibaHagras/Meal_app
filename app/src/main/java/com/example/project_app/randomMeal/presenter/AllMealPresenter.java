package com.example.project_app.randomMeal.presenter;

import com.example.project_app.model.Meal;

public interface AllMealPresenter {
    public void getMeal();
    public void addtoFav(Meal meal);
    public void addtoPlan(Meal meal);
    public void getCtegory();
    public void getArea();

}
