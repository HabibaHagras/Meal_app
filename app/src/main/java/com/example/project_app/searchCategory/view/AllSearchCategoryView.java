package com.example.project_app.searchCategory.view;

import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;

import java.util.List;

public interface AllSearchCategoryView {
    public void showdata(List<Meal> products);
    public void showErrorMsg(String error);
    public void deleteProduct(Meal meal);
    void onLoading();

}
