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

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterIm implements SearchPresenter , NetworkCallback {

    private AllSearchView _view ;
    private mealRepository _Repository;
    private Disposable textChangeDisposable;

    public SearchPresenterIm(AllSearchView _view, mealRepository _Repository) {
        this._view = _view;
        this._Repository = _Repository;
    }

    @Override
    public void getsearch(String mealWord) {
        textChangeDisposable = Observable.just(mealWord)
                .map(String::toLowerCase)
                .defaultIfEmpty("")
                .observeOn(Schedulers.io())
                .switchMap(lowercasedTerm -> _Repository.getAllMealsSearch(this, lowercasedTerm))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> _view.showdata(meals),
                        error_msg -> _view.showErrorMsg("Sorry This Meal isn't in Our Meals")
                );
//        _Repository.getAllMealsSearch(this,mealWord)
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(iteam -> _view.showdata(iteam));

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
