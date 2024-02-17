package com.example.project_app.plan.presenter;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project_app.favMeals.view.AllFavView;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;
import com.example.project_app.plan.view.AllPlanView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenterIm implements PlanPresenter {
    private AllPlanView _view ;
    private mealRepository _Repository;

    public PlanPresenterIm(AllPlanView _view, mealRepository _Repository) {
        this._view = _view;
        this._Repository = _Repository;
    }

    @Override
    public void getPlan() {

        _Repository.getStoredProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(products -> {
                    String currentUserEmail = getCurrentUserEmail();
                    String day = _view.getDayFromIntent();
                    List<Meal> userMeals = filterMealsByEmail(products, currentUserEmail,day);
                    _view.showdata(userMeals);
                });
//        _Repository.getStoredProduct();

    }
    private String getCurrentUserEmail() {
        SharedPreferences sp = _view.getContext().getApplicationContext().getSharedPreferences("useremail", Context.MODE_PRIVATE);
        return sp.getString("userEmail", "");
    }
    private List<Meal> filterMealsByEmail(List<Meal> allMeals, String userEmail ,String day) {
        List<Meal> userMeals = new ArrayList<>();
        for (Meal meal : allMeals) {
            if (meal != null && meal.getUserEmail() != null && meal.getUserEmail().equals(userEmail)&& meal.getDay() != null&& meal.getDay().equals(day)) {
                userMeals.add(meal);
            }
        }
        return userMeals;
    }
    @Override
    public void deleteproductPlan(Meal meal) {
        _Repository.deleteMeal(meal);


    }
}
