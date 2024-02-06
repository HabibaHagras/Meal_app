package com.example.project_app.randomMeal.presenter;

import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;
import com.example.project_app.network.NetworkCallback;
import com.example.project_app.randomMeal.view.AllCategoryView;
import com.example.project_app.randomMeal.view.AllMealView;

import java.util.List;

public class AllMealPresenterIm implements AllMealPresenter , NetworkCallback {
    private AllMealView _view ;
    private AllCategoryView _allCategoryView ;
    private mealRepository _Repository;
//    private categoryRepository _CategoryRepository;

    public AllMealPresenterIm(AllMealView _view, mealRepository _Repository ,AllCategoryView _allCategoryView ) {
        this._view = _view;
        this._Repository = _Repository;
//        this._CategoryRepository=_CategoryRepository;
        this._allCategoryView=_allCategoryView;
    }

    @Override
    public void getMeal() {
        _Repository.getAllMeals(this);

    }

    @Override
    public void addtoFav(Meal product) {
        _Repository.insertMeal(product);

    }

    @Override
    public void getCtegory() {
        _Repository.getAllCategories(this);
    }

    @Override
    public void onSucessResult(List<Meal> products) {
        _view.showdata(products);

    }

    @Override
    public void onFailuer(String error_msg) {
        _view.showErrorMsg(error_msg);
    }

    @Override
    public void onSucessResultCategory(List<Category> categories) {
        _allCategoryView.showdataCategory(categories);
    }

    @Override
    public void onFailuerCategory(String error_msg) {
        _view.showErrorMsg(error_msg);

    }
}