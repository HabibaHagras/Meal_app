package com.example.project_app.IteamMeal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project_app.R;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.search.presenter.SearchPresenter;
import com.example.project_app.search.presenter.SearchPresenterIm;
import com.example.project_app.search.view.AllSearchView;
import com.example.project_app.search.view.onClickSearchListener;

import java.util.List;

public class IteamMealSelectedFromCategoryActivity extends AppCompatActivity implements AllSearchView, onClickSearchListener {
    SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iteam_meal_selected_from_category);
        Intent intent = getIntent();
     Meal   meal = (Meal)intent.getSerializableExtra("IteamMealSelectedFromCategoryActivity");

        searchPresenter= new SearchPresenterIm(this,
                mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                        MealLocalDataSourceIm.getInstance(this)
                ));
//        searchPresenter.getsearch(meal);
        System.out.println(meal);
        OnCartclicfk(meal);
    }

    @Override
    public void showdata(List<Meal> products) {

    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void deleteProduct(Meal meal) {

    }

    @Override
    public void OnCartclick(Meal meal) {
        Intent intent = new Intent(getApplicationContext(), IteamMealActivity.class);
        intent.putExtra("MEAL_KEY", meal);
        startActivity(intent);
    }

    @Override
    public void OnCartclicfk(Meal meal) {
        Intent intent = new Intent(getApplicationContext(), IteamMealActivity.class);
        intent.putExtra("MEAL_KEY", meal);
        startActivity(intent);    }
}