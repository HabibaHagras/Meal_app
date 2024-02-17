package com.example.project_app.IteamCategory.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.project_app.IteamMeal.view.IteamMealActivity;
import com.example.project_app.IteamMeal.view.IteamMealSelectedFromCategoryActivity;
import com.example.project_app.R;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.randomMeal.view.AllMealView;
import com.example.project_app.searchCategory.presenter.SearchCategoryPresenter;
import com.example.project_app.searchCategory.presenter.SearchCategoryPresenterIm;
import com.example.project_app.searchCategory.view.AllSearchCategoryView;
import com.example.project_app.searchCategory.view.OnclickSearchCaregory;
import com.example.project_app.searchCategory.view.SearchCartegoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class IteamCategoryActivity extends AppCompatActivity  implements AllSearchCategoryView, OnclickSearchCaregory {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MealClient mealClient;
    SearchCartegoryAdapter searchAdapter ;
    AppDataBase dp;
    MealDAO DAO;
    List<Meal> mealList;
    String category;
    SearchCategoryPresenter searchPresenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iteam_category);
        Intent intent = getIntent();
        progressBar = findViewById(R.id.progressBar);
        category = intent.getStringExtra("Category_KEY");
        recyclerView = findViewById(R.id.rv__iteam_category);  // Initialize recyclerView
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchCartegoryAdapter(this, new ArrayList<>(),this);

        recyclerView.setAdapter(searchAdapter);

        searchPresenter= new SearchCategoryPresenterIm(this,
                mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                        MealLocalDataSourceIm.getInstance(this)
                ));
        searchPresenter.getsearchCategory(category);
    }

    @Override
    public void showdata(List<Meal> products) {
        if (products != null && products.isEmpty()) {
            progressBar.setVisibility(View.INVISIBLE);
            showNoMealsDialog();
        } else {
            searchAdapter.SetList(products);
            searchAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showErrorMsg(String error) {

    }



    @Override
    public void deleteProduct(Meal meal) {

    }
    private void showNoMealsDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("No meals found with this name").setTitle("No Results");
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    @Override
    public void OnCartclick(Meal meal) {

        Intent intent = new Intent(getApplicationContext(), IteamMealSelectedFromCategoryActivity.class);
        intent.putExtra("IteamMealSelectedFromCategoryActivity", meal.getStrMeal());
        startActivity(intent);
    }
    @Override
    public void onLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}