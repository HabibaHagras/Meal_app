package com.example.project_app.randomMeal.presenter;

import com.example.project_app.model.Area;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;
import com.example.project_app.network.NetworkCallback;
import com.example.project_app.randomMeal.view.AllAreaView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class AllAreaPresenterIm implements AllAreaPresenter, NetworkCallback {
    private AllAreaView _allAreaView;
    private mealRepository _Repository;

    public AllAreaPresenterIm(AllAreaView _allAreaView, mealRepository _Repository) {
        this._allAreaView = _allAreaView;
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

    }

    @Override
    public void onFailuerCategory(String error_msg) {

    }

    @Override
    public void onSucessResultArea(List<Area> areas) {
        _allAreaView.showdataAreas(areas);

    }

    @Override
    public void onFailuerArea(String errorr_msg) {

    }

    @Override
    public void getArea() {
        _Repository.getAllAreas(this).observeOn(AndroidSchedulers.mainThread()).subscribe(iteam -> _allAreaView.showdataAreas(iteam));

    }
}
