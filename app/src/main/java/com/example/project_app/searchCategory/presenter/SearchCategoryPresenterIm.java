package com.example.project_app.searchCategory.presenter;

import com.example.project_app.model.Area;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;
import com.example.project_app.network.NetworkCallback;
import com.example.project_app.randomMeal.view.AllCategoryView;
import com.example.project_app.search.presenter.SearchPresenter;
import com.example.project_app.search.view.AllSearchView;
import com.example.project_app.searchCategory.view.AllSearchCategoryView;

import java.util.List;

public class SearchCategoryPresenterIm implements SearchCategoryPresenter, NetworkCallback {
    private mealRepository _Repository;
    private AllSearchCategoryView _allCategoryView ;


    public SearchCategoryPresenterIm(AllSearchCategoryView _view, mealRepository _Repository) {
        this._allCategoryView = _view;
        this._Repository = _Repository;
    }

    @Override
    public void onSucessResult(List<Meal> meals) {
        _allCategoryView.showdata(meals);
    }

    @Override
    public void onFailuer(String error_msg) {

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


    @Override
    public void getsearchCategory(String categoryWord) {
        _allCategoryView.onLoading();
        _Repository.getAllMealsSearchCategory(this,categoryWord);

    }

}
