package com.example.project_app.searchCategory.presenter;

import android.annotation.SuppressLint;

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
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchCategoryPresenterIm implements SearchCategoryPresenter, NetworkCallback {
    private mealRepository _Repository;
    private AllSearchCategoryView _allCategoryView ;
    private Disposable textChangeDisposable;


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
        _allCategoryView.showErrorMsg(error_msg);


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


    @SuppressLint("CheckResult")
    @Override
    public void getsearchCategory(String categoryWord) {
        _allCategoryView.onLoading();
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    emitter.onNext(categoryWord);
                    emitter.onComplete();
                })
                .map(String::toLowerCase)
                .debounce(500, TimeUnit.MILLISECONDS)
                .defaultIfEmpty("")
                .observeOn(Schedulers.io())
                .concatMap(lowercasedTerm -> _Repository.getAllMealsSearchCategory(this, lowercasedTerm))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> _allCategoryView.showdata(meals),
                        error_msg -> _allCategoryView.showErrorMsg("Sorry, This Meal isn't in Our Meals")
                );
//        Observable.just(categoryWord)
//                .map(String::toLowerCase)
//                .debounce(500, TimeUnit.MILLISECONDS)
//               .defaultIfEmpty("")
//                .observeOn(Schedulers.io())
//                .concatMap(lowercasedTerm -> _Repository.getAllMealsSearchCategory(this, lowercasedTerm))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        meals -> _allCategoryView.showdata(meals),
//                        error_msg -> _allCategoryView.showErrorMsg("Sorry This Meal isn't in Our Meals")
//                );
//                .flatMap(word -> Observable.fromArray(word.split(""))) // Split the word into characters
//                .map(String::toLowerCase)
//                .debounce(500, TimeUnit.MILLISECONDS)
//                .defaultIfEmpty("")
//                .concatMap(lowercasedTerm -> _Repository.getAllMealsSearchCategory(this, lowercasedTerm))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        meals -> {
//                            _allCategoryView.showdata(meals);
//                        },
//                        error_msg -> {
//                            _allCategoryView.showErrorMsg("Sorry, This Category isn't in Our Meals");
//                        }
//                );


        // _Repository.getAllMealsSearchCategory(this,categoryWord).observeOn(AndroidSchedulers.mainThread()).subscribe(iteam -> _allCategoryView.showdata(iteam));

    }
    @Override
    public void getMeal() {
        _Repository.getAllMeals(this).observeOn(AndroidSchedulers.mainThread()).subscribe(iteam -> _allCategoryView.showdata(iteam));

    }
//    @Override
//    public void getCtegory() {
//        _Repository.getAllCategories(this).observeOn(AndroidSchedulers.mainThread()).subscribe(iteam -> _allCategoryView.showdata(iteam));
//
//    }

}
