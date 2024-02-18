package com.example.project_app.randomMeal.presenter;

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
    private AllCategoryView _allCategoryView ;
    private AllAreaView _allAreaView;
    private mealRepository _Repository;
//    private categoryRepository _CategoryRepository;

    public AllMealPresenterIm(AllMealView _view, mealRepository _Repository ,AllCategoryView _allCategoryView ,AllAreaView _allAreaView) {
        this._view = _view;
        this._Repository = _Repository;
//        this._CategoryRepository=_CategoryRepository;
        this._allCategoryView=_allCategoryView;
        this._allAreaView=_allAreaView;
    }

    @Override
    public void getMeal() {
        _Repository.getAllMeals(this).observeOn(AndroidSchedulers.mainThread()).subscribe(iteam -> _view.showdata(iteam));

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

    @Override
    public void getCtegory() {
        _Repository.getAllCategories(this).observeOn(AndroidSchedulers.mainThread()).subscribe(iteam -> _allCategoryView.showdataCategory(iteam));

    }

    @Override
    public void getArea() {
        _Repository.getAllAreas(this).observeOn(AndroidSchedulers.mainThread()).subscribe(iteam -> _allAreaView.showdataAreas(iteam));

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

    @Override
    public void onSucessResultArea(List<Area> areas) {
        _allAreaView.showdataAreas(areas);
    }

    @Override
    public void onFailuerArea(String errorr_msg) {

    }
}
