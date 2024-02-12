package com.example.project_app.favMeals.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project_app.MainActivity;
import com.example.project_app.R;
import com.example.project_app.databinding.ActivityFavBinding;
import com.example.project_app.databinding.ActivityRandomMealBinding;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.favMeals.presenter.FavPresenter;
import com.example.project_app.favMeals.presenter.FavPresenterIm;
import com.example.project_app.model.Meal;
import com.example.project_app.model.Repository;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.plan.view.Day_PlanActivity;
import com.example.project_app.randomMeal.view.RandomMealActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FavActivity extends AppCompatActivity implements OnClickFavListener, AllFavView {
    RecyclerView favRecyclerView;
    LinearLayoutManager linearLayoutManager;
    MealClient mealClient;
    FavAdapter favproductAdapter;
    AppDataBase dp;
    MealDAO DAO;
    Repository repo ;
    FavPresenter favPresenter;
    private ActivityFavBinding binding;
    String currentUserEmail;
    Meal  mael;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityFavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_fav);
        currentUserEmail = getIntent().getStringExtra("currentUserEmail");
        SharedPreferences sp = getApplicationContext().getSharedPreferences("useremail", Context.MODE_PRIVATE);
        String email= sp.getString("userEmail","");
        favRecyclerView=findViewById(R.id.rv_fav);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        favRecyclerView.setLayoutManager(linearLayoutManager);
        mealClient=MealClient.getInstance();
        favproductAdapter = new FavAdapter(this, new ArrayList<>(),this);
        favRecyclerView.setAdapter(favproductAdapter);
        favRecyclerView.setLayoutManager(linearLayoutManager);
        dp=AppDataBase.getInstance(this);
        DAO=dp.getmealDAO();
        repo=Repository.getInstance(this);
        // favproductAdapter.SetList( repo.getStoredProduct());
        favproductAdapter.notifyDataSetChanged();
        favPresenter=new FavPresenterIm(this, mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                MealLocalDataSourceIm.getInstance(this)));
        LiveData<List<Meal>> productList=DAO.getFavoriteMealsForUser(email);
        productList.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                favproductAdapter.SetList(meals);
                favproductAdapter.notifyDataSetChanged();

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_fav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId()==R.id.bottom_fav){
                return true;
            }
            else if (item.getItemId()==R.id.bottomhome) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
                return true;
            }
            else if(item.getItemId()==R.id.buttom_dashboard) {
                startActivity(new Intent(getApplicationContext(), RandomMealActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
                return true;
            }
            else if(item.getItemId()==R.id.planbotton) {
                startActivity(new Intent(getApplicationContext(), Day_PlanActivity.class));
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
    favPresenter.getMeal();
    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void deleteProduct(Meal meal) {
        favPresenter.deleteproduct(meal);


    }

    @Override
    public void onFavProductClick(Meal meal) {
        Toast.makeText(FavActivity.this,"DELETED",Toast.LENGTH_SHORT).show();
        deleteProduct(meal);
    }
}