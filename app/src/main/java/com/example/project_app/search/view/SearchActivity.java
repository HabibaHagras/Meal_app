package com.example.project_app.search.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.project_app.R;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.randomMeal.presenter.AllMealPresenter;
import com.example.project_app.randomMeal.presenter.AllMealPresenterIm;
import com.example.project_app.randomMeal.view.RycAdapter;
import com.example.project_app.search.presenter.SearchPresenter;
import com.example.project_app.search.presenter.SearchPresenterIm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity implements  AllSearchView, onClickSearchListener {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MealClient mealClient;
    SearchAdapter searchAdapter ;
    AppDataBase dp;
    MealDAO DAO;
    List<Meal> mealList;
    SearchPresenter searchPresenter;
    EditText searchEditText;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.rv_search);  // Initialize recyclerView
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchAdapter(this, new ArrayList<>(),this);

        recyclerView.setAdapter(searchAdapter);
        searchEditText =findViewById(R.id.searchEditText);

        searchPresenter= new SearchPresenterIm(this,
                mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                        MealLocalDataSourceIm.getInstance(this)
                ));
        Observable.create((ObservableOnSubscribe<String>) emitter ->
                        searchEditText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                                emitter.onNext(charSequence.toString());
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                            }
                        }))
                .debounce(300, TimeUnit.MILLISECONDS)
                .map(String::toLowerCase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchTerm -> {
                    searchPresenter.getsearch(searchTerm);
                });
    }







    @Override
    public void showdata(List<Meal> products) {
        searchAdapter.SetList(products);
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String error) {
        AlertDialog.Builder alertDialog =new AlertDialog.Builder(this);
        alertDialog.setMessage(error).setTitle("An Error Equre");
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    @Override
    public void deleteProduct(Meal meal) {

    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.rv_search);  // Initialize recyclerView
        searchAdapter = new SearchAdapter(this, new ArrayList<>(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(searchAdapter);
    }

    private List<Meal> filterNames(Meal searchTerm) {
        List<Meal> filteredMeals = new ArrayList<>();
        for (Meal meal : mealList) {
            if (meal.getStrMeal().toLowerCase().contains(searchTerm.toString().toLowerCase())) {
                filteredMeals.add(meal);
            }
        }
        return filteredMeals;
    }
}