package com.example.project_app.planMeals.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project_app.MainActivity;
import com.example.project_app.R;
import com.example.project_app.databinding.ActivityFavBinding;
import com.example.project_app.databinding.ActivityPlanBinding;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.dp.MealPlanDAO;
import com.example.project_app.favMeals.presenter.FavPresenter;
import com.example.project_app.favMeals.presenter.FavPresenterIm;
import com.example.project_app.favMeals.view.AllFavView;
import com.example.project_app.favMeals.view.FavActivity;
import com.example.project_app.favMeals.view.FavAdapter;
import com.example.project_app.favMeals.view.OnClickFavListener;
import com.example.project_app.model.Meal;
import com.example.project_app.model.MealPlan;
import com.example.project_app.model.Repository;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.planMeals.presenter.PlanPresenter;
import com.example.project_app.planMeals.presenter.PlanPresenterIm;
import com.example.project_app.randomMeal.view.RandomMealActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity  implements OnClickPlanListener, AllPlanView {
    RecyclerView planRecyclerView;
    LinearLayoutManager linearLayoutManager;
    MealClient mealClient;
    PlanAdapter planAdapter;
    AppDataBase dp;
    MealDAO DAO;
    Repository repo ;
    PlanPresenter planPresenter;
    String day;
    private ActivityPlanBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityPlanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        super.onCreate(savedInstanceState);
        day = getIntent().getStringExtra("day");
        setContentView(R.layout.activity_plan);
        planRecyclerView=findViewById(R.id.rv_plan);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        planRecyclerView.setLayoutManager(linearLayoutManager);
        mealClient=MealClient.getInstance();
        planAdapter = new PlanAdapter(this, new ArrayList<>(),this);
        planRecyclerView.setAdapter(planAdapter);
        planRecyclerView.setLayoutManager(linearLayoutManager);
        dp=AppDataBase.getInstance(this);
        DAO=dp.getmealDAO();
        repo=Repository.getInstance(this);
        planAdapter.notifyDataSetChanged();
        planPresenter=new PlanPresenterIm(this, mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                MealLocalDataSourceIm.getInstance(this)));
        //pass day by intent
        LiveData<List<Meal>> productList=DAO.getAllMealsPlan(day);
        productList.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                planAdapter.SetList(meals);
                planAdapter.notifyDataSetChanged();

            }
        });



    }

    @Override
    public void showdata(List<Meal> products) {
        planPresenter.getMealPlan(day);
    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void deleteProductPlan(Meal mealPlan, String day) {
        planPresenter.deleteproductPlan(mealPlan, day);

    }

    @Override
    public void onPlanProductClick(Meal mealPlan, String day) {
        Toast.makeText(PlanActivity.this,"DELETED",Toast.LENGTH_SHORT).show();
        deleteProductPlan(mealPlan,day);
    }
}