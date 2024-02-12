package com.example.project_app.planMeals.presenter;

import com.example.project_app.favMeals.presenter.FavPresenter;
import com.example.project_app.model.Meal;
import com.example.project_app.model.MealPlan;
import com.example.project_app.model.mealRepository;
import com.example.project_app.planMeals.view.AllPlanView;

public class PlanPresenterIm implements PlanPresenter {
    private AllPlanView _view;
    private mealRepository _Repository;

    public PlanPresenterIm(AllPlanView _view, mealRepository _Repository) {
        this._view = _view;
        this._Repository = _Repository;
    }


    @Override
    public void getMealPlan(String day) {
        _Repository.getStoredProductPlan(day);
    }



    @Override
    public void deleteproductPlan(Meal mealPlan, String day) {
        _Repository.deleteMealPlan(mealPlan,day);

    }
}
