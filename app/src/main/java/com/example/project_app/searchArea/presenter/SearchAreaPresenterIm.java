package com.example.project_app.searchArea.presenter;

import com.example.project_app.model.Area;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;
import com.example.project_app.network.NetworkCallback;
import com.example.project_app.searchArea.view.SearchAreaView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchAreaPresenterIm implements SearchAreaPresenter, NetworkCallback {
    private mealRepository _Repository;
    private SearchAreaView searchAreaView ;
   private Disposable textChangeDisposable;


    public SearchAreaPresenterIm( SearchAreaView searchAreaView,mealRepository _Repository) {
        this._Repository = _Repository;
        this.searchAreaView = searchAreaView;
    }

    @Override
    public void onSucessResult(List<Meal> meals) {
        searchAreaView.showdata(meals);
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
    public void getsearchArea(String Area) {
        textChangeDisposable = Observable.just(Area)
                .flatMap(word -> Observable.fromArray(word)) // Split the word into characters
                .map(String::toLowerCase)
                .debounce(500, TimeUnit.MILLISECONDS)  // Introduce a delay of 500 milliseconds
                .defaultIfEmpty("")
                .observeOn(Schedulers.io())
                .switchMap(lowercasedTerm -> _Repository.getAllMealsSearchArea(this, Area))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> searchAreaView.showdata(meals),
                        error_msg -> searchAreaView.showErrorMsg("Sorry This Area isn't in Our Meals")
                );

    }
}
