package com.example.project_app.randomMeal.presenter;

import android.annotation.SuppressLint;

import com.example.project_app.model.Area;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;
import com.example.project_app.network.NetworkCallback;
import com.example.project_app.randomMeal.view.AllAreaView;
import com.example.project_app.randomMeal.view.AllCategoryView;
import com.example.project_app.randomMeal.view.AllMealView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllMealPresenterIm implements AllMealPresenter , NetworkCallback {
    private AllMealView _view ;

    private mealRepository _Repository;

    public AllMealPresenterIm(AllMealView _view, mealRepository _Repository ) {
        this._view = _view;
        this._Repository = _Repository;
    }

    @Override
    public void getMeal() {
        _Repository.getAllMeals(this).observeOn(AndroidSchedulers.mainThread()).subscribe(iteam -> _view.showdata(iteam));

    }

    @SuppressLint("CheckResult")
    @Override
    public void addtoFav(Meal product) {
        _Repository.insertMeal(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> _view.showErrorMsg("Add to favourite successfully"),
                        error -> _view.showErrorMsg(error.getMessage())
                );
//        _Repository.insertMeal(product);

    }

    @Override
    public void addtoPlan(Meal meal) {
        _Repository.insertMeal(meal);


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
    }

    @Override
    public void onFailuerCategory(String error_msg) {
        _view.showErrorMsg(error_msg);

    }

    @Override
    public void onSucessResultArea(List<Area> areas) {
    }

    @Override
    public void onFailuerArea(String errorr_msg) {

    }
}
