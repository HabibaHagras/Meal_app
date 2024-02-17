package com.example.project_app.IteamMeal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.project_app.IteamMeal.presenter.IteamMealPresenter;
import com.example.project_app.IteamMeal.presenter.IteamMealPresenterIm;
import com.example.project_app.R;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.randomMeal.view.AllMealView;
import com.example.project_app.search.presenter.SearchPresenter;
import com.example.project_app.search.presenter.SearchPresenterIm;
import com.example.project_app.search.view.AllSearchView;
import com.example.project_app.search.view.onClickSearchListener;

import java.util.List;

public class IteamMealSelectedFromCategoryActivity extends AppCompatActivity implements IteamMealView {
    IteamMealPresenter searchPresenter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_iteam_meal_selected_from_category);
        progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();
     String   meal = intent.getStringExtra("IteamMealSelectedFromCategoryActivity");

        searchPresenter= new IteamMealPresenterIm(this,
                mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                        MealLocalDataSourceIm.getInstance(this)
                ));
        searchPresenter.getMeal(meal);
        System.out.println(meal);
   //     onLoading();
    }

    @Override
    public void showdata(List<Meal> products) {
        progressBar.setVisibility(View.VISIBLE);
        Meal meal = products.get(0);
        Intent intent = new Intent(getApplicationContext(), IteamMealActivity.class);
        intent.putExtra("MEAL_KEY",meal);
        startActivity(intent);

    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void addProduct(Meal product) {

    }

//    @Override
//    public void addPlan(Meal plan_meal) {
//
//    }
//
//    @Override
//    public void onLoading() {
//        progressBar.setVisibility(View.VISIBLE);
//
//    }


}


