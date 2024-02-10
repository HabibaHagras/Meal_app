package com.example.project_app.randomMeal.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project_app.LoginActivity;
import com.example.project_app.MainActivity;
import com.example.project_app.R;
import com.example.project_app.databinding.ActivityMainBinding;
import com.example.project_app.databinding.ActivityRandomMealBinding;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.favMeals.view.FavActivity;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.randomMeal.presenter.AllMealPresenter;
import com.example.project_app.randomMeal.presenter.AllMealPresenterIm;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    private ActivityRandomMealBinding binding;
    String currentUserEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRandomMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_random_meal);
        currentUserEmail = getIntent().getStringExtra("currentUserEmail");

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.buttom_dashboard);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId()==R.id.buttom_dashboard){
                return true;
            }
            else if (item.getItemId()==R.id.bottomhome) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
                return true;
            }
            else if(item.getItemId()==R.id.bottom_fav) {
                //startActivity(new Intent(getApplicationContext(), FavActivity.class));
                Intent intent = new Intent(getApplicationContext(), FavActivity.class);
                intent.putExtra("currentUserEmail", currentUserEmail);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
                return true;
            }
            else {
                return false;
            }
        });


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
        meal.setUserEmail(currentUserEmail); // Replace currentUserEmail with the actual user's email

        Toast.makeText(RandomMealActivity.this,"added",Toast.LENGTH_SHORT).show();
        addProduct(meal);
    }
}