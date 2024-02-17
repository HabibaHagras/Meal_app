package com.example.project_app.IteamMeal.view;

import com.example.project_app.model.Meal;

import java.util.List;

public interface IteamMealView {
    public void showdata(List<Meal> products);
    public void showErrorMsg(String error);
    public void addProduct(Meal product);

}
