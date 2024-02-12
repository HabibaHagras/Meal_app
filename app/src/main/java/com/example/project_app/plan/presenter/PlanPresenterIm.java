package com.example.project_app.plan.presenter;

import com.example.project_app.favMeals.view.AllFavView;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;
import com.example.project_app.plan.view.AllPlanView;

public class PlanPresenterIm implements PlanPresenter {
    private AllPlanView _view ;
    private mealRepository _Repository;

    public PlanPresenterIm(AllPlanView _view, mealRepository _Repository) {
        this._view = _view;
        this._Repository = _Repository;
    }

    @Override
    public void getPlan() {
        _Repository.getStoredProduct();

    }

    @Override
    public void deleteproductPlan(Meal meal) {
        _Repository.deleteMeal(meal);


    }
}
