package com.example.project_app.searchArea.view;

import com.example.project_app.model.Meal;

import java.util.List;

public interface SearchAreaView {
    public void showdata(List<Meal> products);
    public void showErrorMsg(String error);
    public void deleteProduct(Meal meal);
}
