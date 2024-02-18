package com.example.project_app.plan.view;

import android.content.Context;

import com.example.project_app.model.Meal;

import java.util.List;

public interface AllPlanView {
    public void showdata(List<Meal> products);
    public void showErrorMsg(String error);
    public void deleteProduct(Meal meal);
    String getDayFromIntent();
    Context getContext();

}
