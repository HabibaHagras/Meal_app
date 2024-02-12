package com.example.project_app.Day.presenter;

import com.example.project_app.Day.view.AllDayView;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;
import com.example.project_app.randomMeal.presenter.AllMealPresenter;
import com.example.project_app.randomMeal.view.AllCategoryView;
import com.example.project_app.randomMeal.view.AllMealView;

public class DayPresenterIm implements DayPresenter {
    private AllDayView _view ;
    private mealRepository _Repository;

    public DayPresenterIm(AllDayView _view, mealRepository _Repository) {
        this._view = _view;
        this._Repository = _Repository;
    }

    @Override
    public void addtoPlan(Meal meal, String day) {
        _Repository.insertMeal(meal,day);
    }
}
