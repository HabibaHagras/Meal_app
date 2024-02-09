package com.example.project_app.randomMeal.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.project_app.R;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.randomMeal.presenter.AllMealPresenter;
import com.example.project_app.randomMeal.presenter.AllMealPresenterIm;

import java.util.ArrayList;
import java.util.List;

public class RandomMealActivity extends AppCompatActivity implements   PutInFavListener , AllMealView  ,AllCategoryView{
    RecyclerView recyclerView;
    RecyclerView recyclerViewofCategory;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManager1;
    MealClient mealClient;
    RycAdapter productAdapter;
    AppDataBase dp;
    MealDAO DAO;
    List<Meal> mealList;
    AllMealPresenter allMealPresenter;
    CategoryAdapter categoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_meal);
        recyclerView = findViewById(R.id.rv_meals);
        recyclerViewofCategory=findViewById(R.id.rv_categories);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ///////////////////
        linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        ////////////////////
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewofCategory.setLayoutManager(linearLayoutManager1);
        mealClient=mealClient.getInstance();
        productAdapter = new RycAdapter(this, new ArrayList<>(),this);
        recyclerView.setAdapter(productAdapter);
        categoryAdapter = new CategoryAdapter(this, new ArrayList<>(),this);
        recyclerViewofCategory.setAdapter(categoryAdapter);
        dp=AppDataBase.getInstance(this);
        DAO=dp.getmealDAO();
        allMealPresenter= new AllMealPresenterIm(this,
                mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                MealLocalDataSourceIm.getInstance(this)
                ),this);
        allMealPresenter.getMeal();
        allMealPresenter.getCtegory();

    }

    @Override
    public void showdata(List<Meal> products) {
        productAdapter.SetList(products);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String error) {
        AlertDialog.Builder alertDialog =new AlertDialog.Builder(this);
        alertDialog.setMessage(error).setTitle("An Error Equre");
        AlertDialog dialog = alertDialog.create();
        dialog.show();

    }

    @Override
    public void addProduct(Meal product) {
        allMealPresenter.addtoFav(product);

    }

    @Override
    public void showdataCategory(List<Category> categories) {
        categoryAdapter.SetList(categories);
        categoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void oPutInFavClick(Meal meal) {
        Toast.makeText(RandomMealActivity.this,"added",Toast.LENGTH_SHORT).show();
        addProduct(meal);
    }
}