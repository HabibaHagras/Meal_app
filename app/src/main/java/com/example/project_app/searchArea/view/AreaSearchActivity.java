package com.example.project_app.searchArea.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

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
import com.example.project_app.searchCategory.presenter.SearchCategoryPresenter;
import com.example.project_app.searchCategory.presenter.SearchCategoryPresenterIm;
import com.example.project_app.searchCategory.view.AllSearchCategoryView;
import com.example.project_app.searchCategory.view.OnclickSearchCaregory;
import com.example.project_app.searchCategory.view.SearchCartegoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class AreaSearchActivity extends AppCompatActivity implements  SearchAreaView,onClickSearchArea {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AreaAdapter searchAdapter ;

    SearchAreaPresenter searchPresenter;
    EditText searchEditTextArea;
    Meal meal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_search);
        recyclerView = findViewById(R.id.rv_searchAREA);  // Initialize recyclerView
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchAdapter = new AreaAdapter(this, new ArrayList<>(),this);
        Intent intent = getIntent();
        meal = (Meal) intent.getSerializableExtra("MEAL_KEY");
        recyclerView.setAdapter(searchAdapter);
        searchEditTextArea =findViewById(R.id.searchEditTextAREA);
        searchPresenter= new SearchAreaPresenterIm(this,
                mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                        MealLocalDataSourceIm.getInstance(this)
                ));
        setupTextWatcher();
    }
    private void setupTextWatcher() {
        searchEditTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String searchTerm = charSequence.toString().toLowerCase();
                searchPresenter.getsearchArea(searchTerm);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    @Override
    public void showdata(List<Meal> products) {
        if (products != null && products.isEmpty()) {
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