package com.example.project_app.IteamArea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.project_app.IteamMeal.view.IteamMealSelectedFromCategoryActivity;
import com.example.project_app.R;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.searchArea.presenter.SearchAreaPresenter;
import com.example.project_app.searchArea.presenter.SearchAreaPresenterIm;
import com.example.project_app.searchArea.view.AreaAdapter;
import com.example.project_app.searchArea.view.SearchAreaView;
import com.example.project_app.searchArea.view.onClickSearchArea;
import com.example.project_app.searchCategory.presenter.SearchCategoryPresenter;
import com.example.project_app.searchCategory.presenter.SearchCategoryPresenterIm;
import com.example.project_app.searchCategory.view.AllSearchCategoryView;
import com.example.project_app.searchCategory.view.OnclickSearchCaregory;
import com.example.project_app.searchCategory.view.SearchCartegoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class IteamAreaActivity extends AppCompatActivity implements SearchAreaView, onClickSearchArea {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MealClient mealClient;
    AreaAdapter searchAdapter ;
    String area;
    SearchAreaPresenter searchPresenter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iteam_area);
        Intent intent = getIntent();
        progressBar = findViewById(R.id.progressBar);
        area = intent.getStringExtra("Area_KEY");
        recyclerView = findViewById(R.id.rv__iteam_area);  // Initialize recyclerView
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchAdapter = new AreaAdapter(this, new ArrayList<>(),this);

        recyclerView.setAdapter(searchAdapter);

        searchPresenter= new SearchAreaPresenterIm(this,
                mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                        MealLocalDataSourceIm.getInstance(this)
                ));
        searchPresenter.getsearchArea(area);
    }

    @Override
    public void showdata(List<Meal> products) {
        if (products != null && products.isEmpty()) {
            progressBar.setVisibility(View.INVISIBLE);
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

    @Override
    public void OnCartclick(Meal meal) {

        Intent intent = new Intent(getApplicationContext(), IteamMealSelectedFromCategoryActivity.class);
        intent.putExtra("IteamMealSelectedFromCategoryActivity", meal.getStrMeal());
        startActivity(intent);
    }
}