package com.example.project_app.randomMeal.view;


import com.example.project_app.model.Area;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;

public interface PutInFavListener {
    void oPutInFavClick(Meal meal);
    void OnPlanClick(Meal meal);
    void OnCartclick(Meal meal);
    void OnCartCategoryclick(Category category);
    void OnCartAreaclick(Area area);
}
