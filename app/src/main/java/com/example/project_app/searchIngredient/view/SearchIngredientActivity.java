package com.example.project_app.searchIngredient.view;

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
import com.example.project_app.searchCategory.presenter.SearchCategoryPresenter;
import com.example.project_app.searchCategory.presenter.SearchCategoryPresenterIm;
import com.example.project_app.searchIngredient.presenter.SearchIngredientPresenter;
import com.example.project_app.searchIngredient.presenter.SearchIngredientPresenterIm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchIngredientActivity extends AppCompatActivity implements SearchIngredientView ,onClickSearchIngredient {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MealClient mealClient;
    SearchIngredientAdapter searchAdapter ;
    AppDataBase dp;
    MealDAO DAO;
    List<Meal> mealList;
    SearchIngredientPresenter searchPresenter;
    EditText searchEditText;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ingredient);
        recyclerView = findViewById(R.id.rv_searchIngredient);  // Initialize recyclerView
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchIngredientAdapter(this, new ArrayList<>(),this);

        recyclerView.setAdapter(searchAdapter);
        searchEditText =findViewById(R.id.searchEditTextIngredient);

        searchPresenter= new SearchIngredientPresenterIm(this,
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
//                .debounce(500, TimeUnit.MILLISECONDS)
//                .distinctUntilChanged()
                .map(String::toLowerCase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchTerm -> {
                    searchPresenter.getsearchIngredient(searchTerm);
                });
    }


    @Override
    public void showdata(List<Meal> products) {
        if (products != null && products.isEmpty()) {
            // Show a dialog indicating no meals found
            showNoMealsDialog();
        } else {
            searchAdapter.SetList(products);
            searchAdapter.notifyDataSetChanged();
        }
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
    private void showNoMealsDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("No meals found with this name").setTitle("No Results");
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }
}