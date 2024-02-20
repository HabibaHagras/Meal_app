package com.example.project_app.randomMeal.presenter;

import com.example.project_app.model.Area;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;
import com.example.project_app.network.NetworkCallback;
import com.example.project_app.randomMeal.view.AllCategoryView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class AllCategoryPresenterIm  implements AllCategoryPresenter , NetworkCallback {
    private AllCategoryView _allCategoryView ;
    private mealRepository _Repository;

    public AllCategoryPresenterIm(AllCategoryView _allCategoryView, mealRepository _Repository) {
        this._allCategoryView = _allCategoryView;
        this._Repository = _Repository;
    }

    @Override
    public void onSucessResult(List<Meal> meals) {

    }

    @Override
    public void onFailuer(String error_msg) {

    }

    @Override
    public void onSucessResultCategory(List<Category> categories) {
        _allCategoryView.showdataCategory(categories);

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
    public void getCtegory() {
        _Repository.getAllCategories(this).observeOn(AndroidSchedulers.mainThread()).subscribe(iteam -> _allCategoryView.showdataCategory(iteam));

    }
}
