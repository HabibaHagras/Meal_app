package com.example.project_app.network;

import com.example.project_app.model.Area;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.Meal;

import java.util.List;

public interface NetworkCallback {
    public void onSucessResult(List<Meal> meals);
    public void onFailuer(String error_msg);
    public void onSucessResultCategory(List<Category> categories);
    public void onFailuerCategory(String error_msg);
    public void onSucessResultArea(List<Area> areas);
    public void onFailuerArea(String errorr_msg);



}
