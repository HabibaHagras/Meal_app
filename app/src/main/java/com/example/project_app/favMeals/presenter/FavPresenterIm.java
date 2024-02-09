package com.example.project_app.favMeals.presenter;

import com.example.project_app.favMeals.view.AllFavView;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;

public class FavPresenterIm implements FavPresenter {
    private AllFavView _view ;
    private mealRepository _Repository;

    public FavPresenterIm(AllFavView _view, mealRepository _Repository) {
        this._view = _view;
        this._Repository = _Repository;
    }



    @Override
    public void getMeal() {
        _Repository.getStoredProduct();

    }

    @Override
    public void deleteproduct(Meal meal) {
        _Repository.deleteMeal(meal);

    }
}
