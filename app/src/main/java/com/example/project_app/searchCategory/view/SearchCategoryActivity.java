package com.example.project_app.searchCategory.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.project_app.R;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.network.NetworkCallback;
import com.example.project_app.search.presenter.SearchPresenter;
import com.example.project_app.search.presenter.SearchPresenterIm;
import com.example.project_app.search.view.SearchAdapter;
import com.example.project_app.searchCategory.presenter.SearchCategoryPresenter;
import com.example.project_app.searchCategory.presenter.SearchCategoryPresenterIm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchCategoryActivity extends AppCompatActivity implements AllSearchCategoryView , OnclickSearchCaregory{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MealClient mealClient;
    SearchCartegoryAdapter searchAdapter ;
    AppDataBase dp;
    MealDAO DAO;
    List<Meal> mealList;
    SearchCategoryPresenter searchPresenter;
    EditText searchEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_category);
        recyclerView = findViewById(R.id.rv_searchCategory);  // Initialize recyclerView
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchCartegoryAdapter(this, new ArrayList<>(),this);

        recyclerView.setAdapter(searchAdapter);
        searchEditText =findViewById(R.id.searchEditTextCategory);

        searchPresenter= new SearchCategoryPresenterIm(this,
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
                    searchPresenter.getsearchCategory(searchTerm);
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


}