package com.example.project_app.search.presenter;

import com.example.project_app.model.Area;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;
import com.example.project_app.network.NetworkCallback;
import com.example.project_app.plan.view.AllPlanView;
import com.example.project_app.randomMeal.view.AllCategoryView;
import com.example.project_app.randomMeal.view.AllMealView;
import com.example.project_app.search.view.AllSearchView;

import java.util.List;

public class SearchPresenterIm implements SearchPresenter , NetworkCallback {

    private AllSearchView _view ;
    private mealRepository _Repository;

    public SearchPresenterIm(AllSearchView _view, mealRepository _Repository) {
        this._view = _view;
        this._Repository = _Repository;
    }

    @Override
    public void getsearch(String mealWord) {
        _Repository.getAllMealsSearch(this,mealWord);

    }
    @Override
    public void getsearchCategory(String categoryWord) {
        _Repository.getAllMealsSearchCategory(this,categoryWord);

    }
    @Override
    public void onSucessResult(List<Meal> meals) {
        _view.showdata(meals);

    }

    @Override
    public void onFailuer(String error_msg) {
        _view.showErrorMsg(error_msg);
    }

    @Override
    public void onSucessResultCategory(List<Category> categories) {

    }

    @Override
    public void onFailuerCategory(String error_msg) {

    }

    @Override
    public void onSucessResultArea(List<Area> areas) {

    }

    @Override
    public void onFailuerArea(String errorr_msg) {

    }
}
