package com.example.project_app.searchIngredient.presenter;

import com.example.project_app.model.Area;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepository;
import com.example.project_app.network.NetworkCallback;
import com.example.project_app.searchCategory.view.AllSearchCategoryView;
import com.example.project_app.searchIngredient.view.SearchIngredientView;

import java.util.List;

public class SearchIngredientPresenterIm  implements  SearchIngredientPresenter , NetworkCallback {
    private mealRepository _Repository;
    private SearchIngredientView searchIngredientView ;

    public SearchIngredientPresenterIm( SearchIngredientView searchIngredientView ,mealRepository _Repository) {
        this._Repository = _Repository;
        this.searchIngredientView = searchIngredientView;
    }

    @Override
    public void getsearchIngredient(String ingredient) {
        _Repository.getAllMealsSearchIngredient(this,ingredient);

    }

    @Override
    public void onSucessResult(List<Meal> meals) {
        searchIngredientView.showdata(meals);

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
}