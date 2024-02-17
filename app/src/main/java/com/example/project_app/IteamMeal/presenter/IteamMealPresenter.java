package com.example.project_app.IteamMeal.presenter;

import com.example.project_app.model.Meal;

public interface IteamMealPresenter {
    public void getMeal(String word_meal);
    public void addtoFav(Meal meal);
    public void addtoPlan(Meal meal);

}
