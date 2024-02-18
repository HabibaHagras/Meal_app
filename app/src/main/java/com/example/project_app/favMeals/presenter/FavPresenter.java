package com.example.project_app.favMeals.presenter;

import com.example.project_app.model.Meal;

public interface FavPresenter {
    public void getMeal();
    public void getMealForUser(String user );
    public void deleteproduct(Meal meal);
}
