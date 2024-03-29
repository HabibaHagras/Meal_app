package com.example.project_app.IteamMeal.presenter;

import com.example.project_app.IteamMeal.view.IteamMealView;
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

public class IteamMealPresenterIm implements  IteamMealPresenter , NetworkCallback {
    private IteamMealView _view ;

    private mealRepository _Repository;

    public IteamMealPresenterIm(IteamMealView _view, mealRepository _Repository) {

        this._view = _view;
        this._Repository = _Repository;
    }



    @Override
    public void onSucessResult(List<Meal> meals) {
        _view.showdata(meals);

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
    public void getMeal(String word_meal) {
        _Repository.getAllMealsSearch(this,word_meal).observeOn(AndroidSchedulers.mainThread()).subscribe(iteam -> _view.showdata(iteam));
    }
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
}
