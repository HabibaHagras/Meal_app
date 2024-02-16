package com.example.project_app.favMeals.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.project_app.favMeals.view.AllFavView;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavPresenterIm implements FavPresenter {
    private AllFavView _view ;
    private mealRepository _Repository;
    private static final String TAG = "FavPresenterIm";

    public FavPresenterIm(AllFavView _view, mealRepository _Repository) {
        this._view = _view;
        this._Repository = _Repository;
    }



    @Override
    public void getMeal() {
        _Repository.getStoredProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(products -> {
                    String currentUserEmail = getCurrentUserEmail();
                    List<Meal> userMeals = filterMealsByEmail(products, currentUserEmail);
                    _view.showdata(userMeals);
                });
//        _Repository.getStoredProduct();

    }

    @Override
    public void getMealForUser(String user) {
        _Repository.getStoredProductforUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(products -> {
                    _view.showdata(products);
                });
    }

    @Override
    public void deleteproduct(Meal meal) {
        _Repository.deleteMeal(meal);

    }
    private String getCurrentUserEmail() {
        SharedPreferences sp = _view.getContext().getApplicationContext().getSharedPreferences("useremail", Context.MODE_PRIVATE);
        return sp.getString("userEmail", "");
    }

    private List<Meal> filterMealsByEmail(List<Meal> allMeals, String userEmail) {
//        List<Meal> userMeals = new ArrayList<>();
//        for (Meal meal : allMeals) {
//            if (meal.getUserEmail().equals(userEmail)) {
//                Log.i(TAG, "filterMealsByEmail: "+userEmail);
//                Log.i(TAG, "filterMealsByEmailMeal: "+meal.getUserEmail());
//                userMeals.add(meal);
//            }
//        }
//        return userMeals;
        List<Meal> userMeals = new ArrayList<>();
        for (Meal meal : allMeals) {
            if (meal != null && meal.getUserEmail() != null && meal.getUserEmail().equals(userEmail)) {
                userMeals.add(meal);
            }
        }
        return userMeals;
}}
