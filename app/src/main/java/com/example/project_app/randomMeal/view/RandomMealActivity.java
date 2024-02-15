package com.example.project_app.randomMeal.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project_app.Day.view.DayActivity;
import com.example.project_app.IteamMeal.view.IteamMealActivity;
import com.example.project_app.MainActivity;
import com.example.project_app.R;
import com.example.project_app.databinding.ActivityRandomMealBinding;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.favMeals.view.FavActivity;
import com.example.project_app.model.Area;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.plan.view.Day_PlanActivity;
import com.example.project_app.randomMeal.presenter.AllMealPresenter;
import com.example.project_app.randomMeal.presenter.AllMealPresenterIm;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class RandomMealActivity extends AppCompatActivity implements   PutInFavListener , AllMealView  ,AllCategoryView ,AllAreaView{
    RecyclerView recyclerView;
    RecyclerView recyclerViewofCategory;
    RecyclerView recyclerViewofCountry;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManager1;
    LinearLayoutManager linearLayoutManager2;
    MealClient mealClient;
    RycAdapter productAdapter;
    AppDataBase dp;
    MealDAO DAO;
    List<Meal> mealList;
    AllMealPresenter allMealPresenter;
    CategoryAdapter categoryAdapter;
    AreaAdapter areaAdapter;
    private ActivityRandomMealBinding binding;
    String currentUserEmail;
    String email;
    Boolean skip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRandomMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_random_meal);
        currentUserEmail = getIntent().getStringExtra("currentUserEmail");
        SharedPreferences sp = getApplicationContext().getSharedPreferences("useremail", Context.MODE_PRIVATE);
         email= sp.getString("userEmail","");

        recyclerView = findViewById(R.id.rv_meals);
        recyclerViewofCategory=findViewById(R.id.rv_categories);
        recyclerViewofCountry=findViewById(R.id.rv_countries);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ///////////////////
        linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        ////////////////////
        linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        //////////////////
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewofCategory.setLayoutManager(linearLayoutManager1);
        recyclerViewofCountry.setLayoutManager(linearLayoutManager2);
        areaAdapter=new AreaAdapter(this, new ArrayList<>(),this);
        recyclerViewofCountry.setAdapter(areaAdapter);
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
                ),this,this);
        allMealPresenter.getMeal();
        allMealPresenter.getCtegory();
        allMealPresenter.getArea();
        skip = getIntent().getBooleanExtra("skip",false);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.getMenu().clear();
        if (skip==true){
            bottomNavigationView.inflateMenu(R.menu.guest_menu);
            bottomNavigationView.setSelectedItemId(R.id.buttom_dashboardguest);
            bottomNavigationView.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.buttom_dashboardguest) {
                    return true;
                }   else
                if (item.getItemId() == R.id.searchguest) {
                    // startActivity(new Intent(getApplicationContext(), RandomMealActivity.class));
                    Intent intent = new Intent(getApplicationContext(), RandomMealActivity.class);
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
        else {
            bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu);
            bottomNavigationView.setSelectedItemId(R.id.buttom_dashboard);
            bottomNavigationView.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.buttom_dashboard) {
                    return true;
                } else if (item.getItemId() == R.id.bottomhome) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.bottom_fav) {
                    //startActivity(new Intent(getApplicationContext(), FavActivity.class));
                    Intent intent = new Intent(getApplicationContext(), FavActivity.class);
                    intent.putExtra("currentUserEmail", currentUserEmail);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.planbotton) {
                    startActivity(new Intent(getApplicationContext(), Day_PlanActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                    return true;
                } else {
                    return false;
                }
            });
        }


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
        meal.setUserEmail(email); // Replace currentUserEmail with the actual user's email

        Toast.makeText(RandomMealActivity.this,"added",Toast.LENGTH_SHORT).show();
        addProduct(meal);
    }

    @Override
    public void OnPlanClick(Meal meal) {
//        meal.setUserEmail(email); // Replace currentUserEmail with the actual user's email

        Toast.makeText(RandomMealActivity.this,"pls choose day",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), DayActivity.class);
        intent.putExtra("MEAL_OBJECT_KEY", meal);
        startActivity(intent);
      //  addProduct(meal);
    }

    @Override
    public void OnCartclick(Meal meal) {
        Intent intent = new Intent(getApplicationContext(), IteamMealActivity.class);
        intent.putExtra("MEAL_KEY", meal);
        startActivity(intent);
    }

    @Override
    public void showdataAreas(List<Area> areas) {
        areaAdapter.SetList(areas);
        areaAdapter.notifyDataSetChanged();


    }
}